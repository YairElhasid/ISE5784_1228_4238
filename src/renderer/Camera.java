package renderer;

import primitives.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.MissingResourceException;

import static primitives.Util.*;

/**
 * class that implements camera
 * @author Sagiv Maoz and Yair Elhasid
 */
public class Camera implements Cloneable{

    /**
     * all the corners for the corner list in adaptive super sampling
     */
    private static final int TOP_LEFT = 0;
    private static final int TOP_RIGHT = 1;
    private static final int BOTTOM_LEFT = 2;
    private static final int BOTTOM_RIGHT = 3;

    private Point location;
    //the center of the view plane:
    private Point pc;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double width = 0.0;
    private double height = 0.0;
    private double distance = 0.0;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;
    private int numRays = 1; // num of rays to each pixel for multi sampling
    private Blackboard blackboard; // an instance of blackboard for multi sampling
    private boolean isAdaptive = false; //if we are using the adaptive super sampling


    private Camera(){};

    /**
     *getter for the location of the camera
     * @return - the location point
     */
    public Point getLocation() {
        return location;
    }

    /**
     *getter for the v To vector of the camera
     * @return - the v To vector
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     *getter for the v Up vector of the camera
     * @return - the v Up vector
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     *getter for the v Right vector of the camera
     * @return - the v Right vector
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     *getter for the width of the camera
     * @return - the width
     */
    public double getWidth() {
        return width;
    }

    /**
     *getter for the height of the camera
     * @return - the height
     */
    public double getHeight() {
        return height;
    }

    /**
     *getter for the distance from the view plane of the camera
     * @return - the distance from the view plane
     */
    public double getDistance() {
        return distance;
    }

    /**
     * internal class for builder design pattern
     * @author Sagiv Maoz and Yair Elhasid
     */
    public static class Builder{
        private final Camera instance = new Camera();

        /**
         * set the location of the camera
         * @param numRays - the number of rays
         * @return - this builder - for concatenation
         */
        public Builder setNumRays(int numRays){
            instance.numRays = numRays;
            return this;
        }

        /**
         * set the direction vectors of the camera (to and up)
         * @param vTo - the to direction
         * @param vUp - the up direction
         * @return - this builder - for concatenation
         */
        public Builder setDirection(Vector vTo, Vector vUp){
            if(!isZero(vTo.dotProduct(vUp)))
                throw new IllegalArgumentException("vTo must be orthogonal with vUp");
            instance.vTo = vTo.normalize();
            instance.vUp = vUp.normalize();
            return this;
        }

        /**
         * set the size of the view plane
         * @param width - the width of the view plane
         * @param height - the height of the view plane
         * @return - this builder - for concatenation
         */
        public Builder setVpSize(double width, double height){
            if(isZero(width) || isZero(height) || width < 0 || height < 0)
                throw new IllegalArgumentException("width and height must be greater than 0");
            instance.width = width;
            instance.height = height;
            return this;
        }

        /**
         * set the distance from the view plane
         * @param distance - the distance from the view plane
         * @return - this builder - for concatenation
         */
        public Builder setVpDistance(double distance){
            if(isZero(distance) || distance < 0)
                throw new IllegalArgumentException("distance must be greater than 0");
            instance.distance = distance;
            return this;
        }

        /**
         * set the adaptive super sampling configuration parameter
         * @param isAdaptive - the configuration parameter
         * @return - this builder - for concatenation
         */
        public Builder setIsAdaptive(boolean isAdaptive){
            instance.isAdaptive = isAdaptive;
            return this;
        }


        /**
         * set the imageWriter of the camera
         * @param imageWriter- the imageWriter of the camera
         * @return - this builder - for concatenation
         */
        public Builder setImageWriter(ImageWriter imageWriter){
            instance.imageWriter = imageWriter;
            return this;
        }

        /**
         * set the rayTracer of the camera
         * @param rayTracer- the rayTracer of the camera
         * @return - this builder - for concatenation
         */
        public Builder setRayTracer(RayTracerBase rayTracer){
            instance.rayTracer = rayTracer;
            return this;
        }

        /**
         * set the number of rays the camera sending to each pixel
         * @param location - the new point
         * @return - this builder - for concatenation
         */
        public Builder setLocation(Point location){
            instance.location = location;
            return this;
        }

        /**
         * return the final product of the builder and calculate the camera missing arguments
         * @return - the final camera
         */
        public Camera build(){
            final String className = "Camera", genarlMessege = "Missing camera argument";
            if (instance.location == null) throw new MissingResourceException(genarlMessege,className,"location");
            if (instance.vUp == null) throw new MissingResourceException(genarlMessege,className,"vUp");;
            if (instance.vTo == null) throw new MissingResourceException(genarlMessege,className,"vTo");;
            if (isZero(instance.width)) throw new MissingResourceException(genarlMessege,className,"width");;
            if (isZero(instance.height)) throw new MissingResourceException(genarlMessege,className,"height");;
            if (isZero(instance.distance)) throw new MissingResourceException(genarlMessege,className,"distance");;
            if(instance.imageWriter == null) throw new MissingResourceException(genarlMessege,className,"imageWriter");
            if(instance.rayTracer == null) throw new MissingResourceException(genarlMessege,className,"rayTracer");
            //set the vRight vector
            instance.vRight = instance.vTo.crossProduct(instance.vUp).normalize();
            //set the center of the view plane point
            instance.pc = instance.location.add(instance.vTo.scale(instance.distance));
            //set the general black board of this camera
            instance.blackboard = Blackboard.getBuilder().setPixelHeight(instance.height/instance.imageWriter.getNy()).setPixelWidth(instance.width/instance.imageWriter.getNx()).setNumRows(instance.numRays)
                    .setVdown(instance.vUp.scale(-1)).setVright(instance.vRight).build();
            try{
                return (Camera) instance.clone(); // Cloneable - get a full copy
            }
            catch(CloneNotSupportedException e){
                throw new RuntimeException("error: ", e);
            }
        }
    }

    /**
     * returns an instance of the Builder class - for the builder design pattern
     * @return - instance of the Builder class
     */
    public static Builder getBuilder(){
        return new Builder();
    }

    /**
     * creates a ray from the camera point to the specified pixel
     * @param nX - the index of the pixel in geometric coordinates (X,)
     * @param nY - the index of the pixel in geometric coordinates (,Y)
     * @param j - the index of the pixel in matrix coordinates [i][]
     * @param i - the index of the pixel in matrix coordinates [][j]
     * @param isCorner if this point is for the blackboard or the construct ray - in the middle
     * @return - the ray
     */
    private Point constructPoint(int nX, int nY, int j, int i, boolean isCorner){
        Point Pij = pc;
        double Rx = width/nX, Ry = height/nY;
        double yi = -(i - ((double)(nY - (isCorner ? 0 : 1)) / 2)) * Ry;
        double xj = (j - ((double)(nX - (isCorner ? 0 : 1)) / 2)) * Rx;
        if (!isZero(xj)) Pij = Pij.add(vRight.scale(xj));
        if (!isZero(yi)) Pij = Pij.add(vUp.scale(yi));
        return  Pij;
    }


    /**
     * creates a ray from the camera point to the specified pixel
     * @param nX - the index of the pixel in geometric coordinates (X,)
     * @param nY - the index of the pixel in geometric coordinates (,Y)
     * @param j - the index of the pixel in matrix coordinates [i][]
     * @param i - the index of the pixel in matrix coordinates [][j]
     * @return - the ray
     */
    public Ray constructRay(int nX, int nY, int j, int i){
        // construct one ray to the middle of the pixel
        return new Ray(location, constructPoint(nX, nY, j, i, false).subtract(location));
    }

    /**
     *return list of the 4 edges of a pixel
     * @param nX - the index of the pixel in geometric coordinates (X,)
     * @param nY - the index of the pixel in geometric coordinates (,Y)
     * @param j - the index of the pixel in matrix coordinates [i][]
     * @param i - the index of the pixel in matrix coordinates [][j]
     * @return list of the corners: [top left, top right, bottom left, bottom right]
     */
    public List<Point> constructFourEdges(int nX, int nY, int j, int i){
        Point center = constructPoint(nX, nY, j, i, false);
        Vector up = vUp.scale(height/(2 * nY)), down = vUp.scale(-height/(2 * nY)), left = vRight.scale(-width/(2 * nX)), right = vRight.scale(width/(2 * nX));
        return new ArrayList(List.of(center.add(up).add(left), center.add(up).add(right), center.add(down).add(left), center.add(down).add(right)));
    }


    /**
     * paint the scene into the imagewriter's image
     */
    public void renderImage(){
        int nX = imageWriter.getNx(), nY = imageWriter.getNy();
        for (int i=0;i< nY;i++){
            for(int j=0;j< nX;j++){
                castRay(nX,nY,j,i);
            }
        }
    }

    /**
     * cast ray from any pixel and paint it
     * @param nX - the index of the pixel in geometric coordinates (X,)
     * @param nY - the index of the pixel in geometric coordinates (,Y)
     * @param j - the index of the pixel in matrix coordinates [i][]
     * @param i - the index of the pixel in matrix coordinates [][j]
     */
    private void castRay(int nX, int nY, int j, int i){
        // regular - one ray to each pixel
        if(numRays == 1){
            imageWriter.writePixel(j, i,rayTracer.traceRay(constructRay(nX,nY,j, i)));
        }
        else{ //if we are doing super sampling:
            if(!isAdaptive){ //with no acceleration
                int count = 0; // how much rays we sent from every pixel
                primitives.Color finalColor = primitives.Color.BLACK;
                // set to the top-left corner of the pixel
                blackboard.setStartingPoint(constructPoint(nX,nY,j, i, true));
                for(Ray ray: blackboard.constructRays(location)){
                    count++;
                    finalColor = finalColor.add(rayTracer.traceRay(ray));
                }
                // calculate the average color
                imageWriter.writePixel(j, i,finalColor.scale(1/(double)count));
            }
            else{//with acceleration
                HashMap<Point, primitives.Color> points = new HashMap<>();
                int initialLevel = (int)(Math.log(numRays - 1) / Math.log(2));
                List<Point> corners = constructFourEdges(nX,nY,j, i);
                imageWriter.writePixel(j, i, traceAdaptiveRays(corners.get(TOP_LEFT), corners.get(TOP_RIGHT), corners.get(BOTTOM_LEFT), corners.get(BOTTOM_RIGHT),points, initialLevel));
            }
        }


    }

    /**
     * paint a grid over the scene into the imagewriter's image
     * @param interval the distance between the grid lines
     * @param color the given color of the grid
     */
    public void printGrid(int interval, primitives.Color color){
        for(int j=0;j< imageWriter.getNx();j++) {
            for(int i=0;i< imageWriter.getNy();i++){
                if(i % interval == 0 || j % interval == 0){
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
    }
    /**
     * export the image to the images directory
     */
    public void writeToImage(){
        imageWriter.writeToImage();
    }

    /**
     * calc the color of a pixel by adaptive super sampling formula
     * @param topLeft the top left corner of the sub pixel
     * @param topRight the top right corner of the sub pixel
     * @param bottomLeft the bottom left corner of the sub pixel
     * @param bottomRight the bottom right corner of the sub pixel
     * @param points all the points and their colors, so we won't calc the same color twice
     * @param initialLevel the level of the recursion
     * @return the color of the pixel of the sub pixel
     */
    private primitives.Color traceAdaptiveRays(Point topLeft, Point topRight, Point bottomLeft, Point bottomRight, HashMap<Point, primitives.Color> points, int initialLevel){
        Point center = topLeft.getMiddle(bottomRight);

        //the end of the recursion
        if(initialLevel == 0){
            return rayTracer.traceRay(new Ray(location, center.subtract(location)));
        }

        primitives.Color topLeftColor = points.getOrDefault(topLeft, null);
        if(topLeftColor == null){
            topLeftColor = rayTracer.traceRay(new Ray(location, topLeft.subtract(location)));
            points.put(topLeft, topLeftColor);
        }

        primitives.Color topRightColor = points.getOrDefault(topRight, null);
        if(topRightColor == null){
            topRightColor = rayTracer.traceRay(new Ray(location, topRight.subtract(location)));
            points.put(topRight, topRightColor);
        }

        primitives.Color bottomLeftColor = points.getOrDefault(bottomLeft, null);
        if(bottomLeftColor == null){
            bottomLeftColor = rayTracer.traceRay(new Ray(location, bottomLeft.subtract(location)));
            points.put(bottomLeft, bottomLeftColor);
        }

        primitives.Color bottomRightColor = points.getOrDefault(bottomRight, null);
        if(bottomRightColor == null){
            bottomRightColor = rayTracer.traceRay(new Ray(location, bottomRight.subtract(location)));
            points.put(bottomRight, bottomRightColor);
        }

        if(topLeftColor.equals(topRightColor) && topLeftColor.equals(bottomLeftColor) && topLeftColor.equals(bottomRightColor)){
            return topLeftColor;
        }
        else{
            Point topMiddle = topLeft.getMiddle(topRight);
            Point bottomMiddle = bottomLeft.getMiddle(bottomRight);
            Point middleLeft = topLeft.getMiddle(bottomLeft);
            Point middleRight = topRight.getMiddle(bottomRight);
            topLeftColor = traceAdaptiveRays(topLeft, topMiddle, middleLeft, center, points, initialLevel - 1).scale(0.25);
            topRightColor = traceAdaptiveRays(topMiddle, topRight, center, middleRight, points, initialLevel - 1).scale(0.25);
            bottomLeftColor = traceAdaptiveRays(middleLeft, center, bottomLeft, bottomMiddle, points, initialLevel - 1).scale(0.25);
            bottomRightColor = traceAdaptiveRays(center, middleRight, bottomMiddle, bottomRight, points, initialLevel - 1).scale(0.25);
            return topLeftColor.add(topRightColor).add(bottomLeftColor).add(bottomRightColor);
        }
    }

}

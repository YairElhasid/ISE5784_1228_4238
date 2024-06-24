package renderer;

import primitives.*;

import java.util.MissingResourceException;

import static primitives.Util.*;

/**
 * class that implements camera
 * @author Sagiv Maoz and Yair Elhasid
 */
public class Camera implements Cloneable{
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
         * @param location - the new point
         * @return - this builder - for concatenation
         */
        public Builder setLocation(Point location){
            instance.location = location;
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
     * @return - the ray
     */
    public Ray constructRay(int nX, int nY, int j, int i){
        Point Pij = pc;
        double Rx = width/nX, Ry = height/nY;
        double yi = -1 * (i - ((double)(nY - 1) / 2)) * Ry;
        double xj = (j - ((double)(nX - 1) / 2)) * Rx;
        if (xj != 0) Pij = Pij.add(vRight.scale(xj));
        if (yi != 0) Pij = Pij.add(vUp.scale(yi));
        return new Ray(location, Pij.subtract(location));
    }

    /**
     * paint the scene into the imagewriter's image
     */
    public void renderImage(){
        int nX = imageWriter.getNx(), nY = imageWriter.getNy();
        for (int i=0;i< nX;i++){
            for(int j=0;j< nY;j++){
                castRay(nX,nY,i, j);
            }
        }
    }
    /**
     * cast ray from any pixel and paint it
     */
    private void castRay(int nX, int nY, int i, int j){
        imageWriter.writePixel(i, j,rayTracer.traceRay(constructRay(nX,nY,j,i)));
    }

    /**
     * paint a grid over the scene into the imagewriter's image
     * @param interval the distance between the grid lines
     * @param color the given color of the grid
     */
    public void printGrid(int interval, primitives.Color color){
        for(int i=0;i< imageWriter.getNx();i++) {
            for(int j=0;j< imageWriter.getNy();j++){
                if(i % interval == 0 || j % interval == 0){
                    imageWriter.writePixel(i, j, color);
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


}

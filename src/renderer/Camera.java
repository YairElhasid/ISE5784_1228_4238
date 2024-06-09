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
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double width = 0.0;
    private double height = 0.0;
    private double distance = 0.0;

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
            if(!isZero(width) || !isZero(height) || width < 0 || height < 0)
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
            if(!isZero(distance) || distance < 0)
                throw new IllegalArgumentException("distance must be greater than 0");
            instance.distance = distance;
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
            instance.vRight = instance.vTo.crossProduct(instance.vUp).normalize();
            return (Camera) instance.clone(); // Cloneable - get a full copy
        }
    }

    /**
     * returns an instance of the Builder class - for the builder design pattern
     * @return - instance of the Builder class
     */
    public static Builder getBuilder(){}

    /**
     * creates a ray from the camera point to the specified pixel
     * @param nX - the index of the pixel in geometric coordinates (X,)
     * @param nY - the index of the pixel in geometric coordinates (,Y)
     * @param j - the index of the pixel in matrix coordinates [i][]
     * @param i - the index of the pixel in matrix coordinates [][j]
     * @return - the ray
     */
    public Ray constructRay(int nX, int nY, int j, int i){}
}

package renderer;

import primitives.*;

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

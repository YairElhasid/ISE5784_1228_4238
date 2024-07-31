package primitives;
import java.util.Objects;

/**
 * The class implements a point object
 * @author Sagiv Maoz and Yair Elhasid
 */
public class Point {
    /**
     * xyz is the coordinate of the point
     */
    final protected Double3 xyz;
    /**
     * origin coordinate
     */
    final public static Point ZERO = new Point(0,0,0);

    /**
     * full constructor
     * @param x - x
     * @param y - y
     * @param z - z
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * constructor that get a Double3 object
     * @param xyz - the Double3 object
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * subtract the second point from the first one
     * @param other the second point
     * @return the result as a new vector
     */
    public Vector subtract(Point other){
        return new Vector(xyz.subtract(other.xyz));
    }

    /**
     * progressing the old point in the direction of the vector
     * @param other - the other vector
     * @return new point - the result
     */
    public Point add(Vector other){
        return new Point(xyz.add(other.xyz));
    }

    /**
     * calculates the squared distance between 2 points
     * @param other -the other point
     * @return the squared distance
     */
    public double distanceSquared(Point other){
        return (xyz.d1 - other.xyz.d1)*(xyz.d1 - other.xyz.d1)
                + (xyz.d2 - other.xyz.d2) * (xyz.d2 - other.xyz.d2)
                + (xyz.d3 - other.xyz.d3) * (xyz.d3 - other.xyz.d3);
    }

    /**
     * return the distance between two points
     * @param other - the second point
     * @return the distance
     */
    public double distance(Point other){
        return Math.sqrt(distanceSquared(other));
    }

    /**
     * function ti grt the point that is in the middle of two points
     * @param other  the other point
     * @return the point in the middle
     */
    public Point getMiddle(Point other){
        return new Point((xyz.d1 + other.xyz.d1) / 2, (xyz.d2 + other.xyz.d2) / 2, (xyz.d3 + other.xyz.d3) / 2);
    }

    @Override
    public String toString() {
        return xyz.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        return obj instanceof Point other && xyz.equals(other.xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xyz.d1, xyz.d2, xyz.d3);
    }
}

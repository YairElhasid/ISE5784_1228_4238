package geometries;

/**
 * an abstract class for geometries with radius
 * @author Sagiv Maoz and Yair Elhasid
 */
abstract public class RadialGeometry implements Geometry {
    /**
     * the radius of the shape
     */
    final protected double radius;
    /**
     * full constructor
     * @param radius - the radius of the shape
     */
    public RadialGeometry(double radius) {
        if (radius < 0)
            throw new IllegalArgumentException("radius cannot be negative");
        this.radius = radius;
    }
}

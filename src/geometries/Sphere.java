package geometries;
import primitives.*;
/**
 * class that implements sphere
 * @author Sagiv Maoz and Yair Elhasid
 */
public class Sphere extends RadialGeometry{
    final private Point center;

    /**
     * full constructor
     * @param center - the center of the sphere
     * @param radius - the radius of the sphere
     */
    public Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    @Override
    public Vector getNormal(Point p) {
        return  p.subtract(center).normalize();
    }

}

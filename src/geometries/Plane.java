package geometries;
import primitives.*;
/**
 * class that implements plane
 * @author Sagiv Maoz and Yair Elhasid
 */
public class Plane implements Geometry{
    final private Point point;
    final private Vector normal;

    /**
     * full constructor
     * @param point - the point
     * @param normal - the normal
     */
    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize();
    }

    /**
     * 3 points constructor
     * @param point1 - first point
     * @param point2 - second point
     * @param point3 - third point
     */
    public Plane(Point point1, Point point2, Point point3 ) {
        normal = (point1.subtract(point2).crossProduct(point2.subtract(point3))).normalize();
        point = point1;
    }

    /**
     * returns the normal vector of the plana
     * @return - the normal
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return normal;
    }
}

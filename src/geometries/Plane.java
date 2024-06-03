package geometries;
import primitives.*;
import static primitives.Util.*;


import java.util.List;

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

    @Override
    public List<Point> findIntsersections(Ray ray) {
        double denominator = normal.dotProduct(ray.getDirection());
        double nominator = 0;
        if (isZero(denominator)) return null;
        try {
             nominator = normal.dotProduct(point.subtract(ray.getHead()));
        }
        // in case that the head of the ray is on the point of the plane
        catch (IllegalArgumentException exp){
            return null;
        }
        double t = nominator / denominator;
        // if the ray start on/after the plane
        if (t<0 || isZero(t)) return null;
        return List.of(point.add(ray.getDirection().scale(t)));
    }
}

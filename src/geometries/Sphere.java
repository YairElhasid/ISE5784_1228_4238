package geometries;
import primitives.*;

import java.util.List;

import static primitives.Util.*;

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

    @Override
    public List<Point> findIntsersections(Ray ray) {
        Point head = ray.getHead();
        Vector direction = ray.getDirection();
        // if the ray head is at the center of the sphere
        if (center.equals(head))
            return List.of(head.add(direction.scale(radius)));
        Vector u = center.subtract(head);
        double tm = alignZero(direction.dotProduct(u));
        //if the ray is outside the sphere
        if(tm < 0 && head.distance(center) >= radius)
            return null;
        double d = Math.sqrt(alignZero(u.lengthSquared() - tm * tm));
        if (d >= radius)
            return null;
        double th = alignZero(Math.sqrt((radius * radius) - (d * d)));
        if(isZero(th))
            return null;
        if(tm <= th)
            return List.of(head.add(direction.scale(th + tm)));
        return List.of(head.add(direction.scale(tm-th)), head.add(direction.scale(th + tm)));
    }

}

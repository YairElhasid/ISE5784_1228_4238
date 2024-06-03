package geometries;
import primitives.*;

import java.util.List;

/**
 * class that implements triangle
 * @author Sagiv Maoz and Yair Elhasid
 */
public class Triangle extends Polygon {
    /**
     * full constructor
     * @param a - first vertex
     * @param b - second vertex
     * @param c - third vertex
     */
    public Triangle(Point a, Point b, Point c) {
        super(a, b, c);
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        List<Point> intersection = plane.findIntsersections(ray);
        if(intersection == null) return null;
        Point p = intersection.getFirst();
        try{
            Vector lastIteration = (vertices.get(1).subtract(vertices.get(0))).crossProduct(vertices.get(0).subtract(p));
            for(int i = 1; i < 3; ++i) {
                Vector currentIteration = (vertices.get((i + 1) % 3).subtract(vertices.get(i))).crossProduct(vertices.get(i).subtract(p));
                if (lastIteration.dotProduct(currentIteration) < 0) return null;
                lastIteration = currentIteration;
            }
        }
        //if there was one or more zero vectors then the point is not on the polygon
        catch(IllegalArgumentException exp) {
            return null;
        }
        return intersection;
    }
}

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
        return List.of();
    }
}

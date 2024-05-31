package geometries;

import primitives.*;
import java.util.*;

/**
 * interface for intersectable shapes
 * @author Sagiv Maoz and Yair Elhasid
 */
public interface Intersectable {

    /**
     * find all the intersections between ray and a shape (or collection of shapes)
     * @param ray- the intresecting ray
     * @return list of the intersection point or null if there isn't
     */
    List<Point> findIntsersections(Ray ray);

}

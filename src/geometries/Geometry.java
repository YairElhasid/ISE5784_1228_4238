package geometries;

import primitives.*;

/**
 * interface for geometrical shapes
 * @author Sagiv Maoz and Yair Elhasid
 */
public interface Geometry extends Intersectable {
    /**
     * ca
     * @param p - a point on the shape
     * @return the normal vector
     */
    public Vector getNormal(Point p);
}
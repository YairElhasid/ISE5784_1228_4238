package geometries;

import primitives.*;
import java.util.*;

/**
 * abstract class for intersectable shapes
 * @author Sagiv Maoz and Yair Elhasid
 */
public abstract class Intersectable {

    /**
     * find all the intersections between ray and a shape (or collection of shapes)
     * @param ray- the intresecting ray
     * @return list of the intersection point or null if there isn't
     */
    public List<Point> findIntsersections(Ray ray){

    }

    /**
     * find all the GeoPoint intersections between ray and a shape (or collection of shapes)
     * @param ray- the intresecting ray
     * @return list of the intersection Geopoint or null if there isn't
     */
    public  List<GeoPoint> findGeoIntsersections(Ray ray){
        return findGeoIntsersectionsHelper(ray);
    }

    /**
     * helping method to findGeoIntsersections implementing the NVI design pattern
     * @param ray- the intresecting ray
     * @return list of the intersection Geopoint or null if there isn't
     */
    protected abstract List<GeoPoint> findGeoIntsersectionsHelper(Ray ray);


    /**
     * PDS for geometry and an Point on it
     * @author Sagiv Maoz and Yair Elhasid
     */
    public static class GeoPoint{
        /**
         * the geometry
         */
        public Geometry geometry;
        /**
         * the point
         */
        public Point point;

        /**
         * constructor
         * @param geometry - the geometry
         * @param point - the point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj) return true;
            return obj instanceof GeoPoint other && other.geometry == geometry && other.point.equals(point);
        }

        @Override
        public String toString() {
            return geometry.toString() + ' ' + point.toString();
        }

    }

}

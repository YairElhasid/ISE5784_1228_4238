package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.addAll;

/**
 * class for collection of shapes
 * @author Sagiv Maoz and Yair Elhasid
 */
public class Geometries extends Intersectable{
    private final LinkedList<Intersectable> intersectables = new LinkedList<>();

    /**
     * empty constructor
     */
    public Geometries(){}
    /**
     * constructor that get unknown amount of geometries
     */
    public Geometries(Intersectable... geometries){
       add(geometries);
    }

    /**
     * add new geometries to the list
     * @param geometries - collection of geometries
     */
    public void add(Intersectable... geometries){
        addAll(intersectables, geometries);
    }

    @Override
    public List<GeoPoint> findGeoIntsersectionsHelper(Ray ray){
        List<GeoPoint> intersections = null;
        for(Intersectable intersectable : intersectables){
            var currentIntersections = intersectable.findGeoIntsersectionsHelper(ray);
            if(currentIntersections != null)
                if(intersections == null)
                    intersections = new LinkedList<>(currentIntersections);
                else
                    intersections.addAll(currentIntersections);
        }
        return intersections;
    }
}

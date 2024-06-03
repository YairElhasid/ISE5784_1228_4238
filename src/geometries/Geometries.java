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
public class Geometries implements Intersectable{
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
    public List<Point> findIntsersections(Ray ray){
        List<Point> intersections = null;
        for(Intersectable intersectable : intersectables){
            List<Point> currentIntersections = intersectable.findIntsersections(ray);
            if(currentIntersections != null){
                if(intersections == null){
                    intersections = currentIntersections;
                }
                else{
                    for(Point intersection : currentIntersections){
                        intersections.add(intersection);
                    }
                }
            }
        }
        return intersections;
    }
}

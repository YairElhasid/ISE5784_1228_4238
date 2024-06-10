package renderer;
import org.junit.jupiter.api.Test;

import geometries.Geometry;
import primitives.Point;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Testing integration between Camera Class (constructRay) and geometries class (find intersections)
 * @author Yair Elhasid and Sagiv Maoz
 */
public class rendererGeometriesIntegrationTest {

    /**
     * helping method that constructs ray through all the pixels and intersects them with a shape
     * @param camera - the camera
     * @param shape - the shape
     * @return - the number of intersections between the ray and the shape
     */
    private int constructAndIntersect(Camera camera ,Geometry shape){
        int counter = 0;
        List<Point> intersections;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                intersections = shape.findIntsersections(camera.constructRay(3, 3, j, i));
                counter += (intersections == null) ? 0 : intersections.size();
            }
        }
        return counter;
    }
    /** test for sphere intersect*/
    @Test
    void testSphereIntersect(){
        Camera camera = Camera.getBuilder().setDirection(new Vector(0,0,1), new Vector(0,1,0)).setLocation(new Point(0,0,0)).setVpDistance(1).setVpSize(3,3).build();
        assertEquals("");
    }


}

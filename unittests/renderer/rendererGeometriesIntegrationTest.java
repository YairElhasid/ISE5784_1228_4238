package renderer;
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

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
    /** test sphere intersections*/
    @Test
    void testSphereIntersect(){
        Camera camera = Camera.getBuilder().setDirection(new Vector(0,0,-1), new Vector(0,1,0)).setLocation(new Point(0,0,0)).setVpDistance(1).setVpSize(3,3).setImageWriter(new ImageWriter("baba",3,3)).setRayTracer(new SimpleRayTracer(new Scene("babi"))).build();
        // TC01: the sphere start after the view plane and has only two intersections
        assertEquals(constructAndIntersect(camera,new Sphere(new Point(0,0,-3),1)),2,"wrong number of intersections in Sphere TC01");
        // TC02: the sphere start before the camera and has nine intersections
        assertEquals(constructAndIntersect(camera,new Sphere(new Point(0,0,-1),6)),9,"wrong number of intersections in Sphere TC02");
        // TC03: the sphere start before the camera and doesn't have intersections
        assertEquals(constructAndIntersect(camera,new Sphere(new Point(0,0,1),0.5)),0,"should has no intersections in Sphere TC03");
        camera = Camera.getBuilder().setDirection(new Vector(0,0,-1), new Vector(0,1,0)).setLocation(new Point(0,0,0.5)).setVpDistance(1).setVpSize(3,3).setImageWriter(new ImageWriter("baba",3,3)).setRayTracer(new SimpleRayTracer(new Scene("babi"))).build();
        // TC04: the sphere start between the camera and the view plane and has 18 intersections
        assertEquals(constructAndIntersect(camera,new Sphere(new Point(0,0,-2.5),2.5)),18,"wrong number of intersections in Sphere TC04");
        // TC05: the sphere start between the camera and the view plane and has 10 intersections
        assertEquals(constructAndIntersect(camera,new Sphere(new Point(0,0,-2),2)),10,"wrong number of intersections in Sphere TC05");

    }

    /** test plane intersections*/
    @Test
    void testPlaneIntersect(){
        Camera camera = Camera.getBuilder().setDirection(new Vector(0,0,-1), new Vector(0,1,0)).setLocation(new Point(0,0,0)).setVpDistance(1).setVpSize(3,3).setImageWriter(new ImageWriter("baba",3,3)).setRayTracer(new SimpleRayTracer(new Scene("babi"))).build();
        // TC01: the plane is parallel to the view plane (9 points)
        assertEquals(9,constructAndIntersect(camera,new Plane(new Point(0,1,-2), new Point(1,-1,-2), new Point(-1,-1,-2))),"wrong number of intersections in Plane TC01" );
        // TC02: the plane is not to the view plane (9 points)
        assertEquals(9,constructAndIntersect(camera,new Plane(new Point(0,1,-2), new Point(1,-1,-2.5), new Point(-1,-1,-2.5))),"wrong number of intersections in Plane TC02" );
        // TC03: the plane is not to 3 of the rays (6 points)
        assertEquals(6,constructAndIntersect(camera,new Plane(new Point(0,1,0), new Point(0,0,-1), new Point(1,0,-1))),"wrong number of intersections in Plane TC03" );

    }

    /** test triangle intersections*/
    @Test
    void testTriangleIntersect() {
        Camera camera = Camera.getBuilder().setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0)).setLocation(new Point(0, 0, 0)).setVpDistance(1).setVpSize(3, 3).setImageWriter(new ImageWriter("baba",3,3)).setRayTracer(new SimpleRayTracer(new Scene("babi"))).build();
        // TC01: small triangle (1 point)
        assertEquals(1, constructAndIntersect(camera, new Triangle(new Point(0, 1, -2), new Point(1, -1, -2), new Point(-1, -1, -2))), "wrong number of intersections in triangle TC01");
        // TC02: bigger triangle (2 points)
        assertEquals(2, constructAndIntersect(camera, new Triangle(new Point(0, 20, -2), new Point(1, -1, -2), new Point(-1, -1, -2))), "wrong number of intersections in triangle TC02");


    }

}

package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * tests for geometries
 * @author Sagiv Maoz and Yair Elhasid
 */
class GeometriesTest {
    Geometries geometries = new Geometries(new Sphere(new Point(4,4,4), 3), new Plane(new Point(1,0,0), new Point(0,1,0), new Point(0,0,1)), new Geometries(new Sphere(new Point(5,4,7), 2), new Polygon(new Point(1,4,4), new Point(4,1,4), new Point(4,4,1))));

    /** Test method for {@link geometries.Geometries#findIntsersections(Ray)} */
    @Test
    void findIntsersections() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: only part of the geometries are intersecting
        assertEquals(3, geometries.findIntsersections(new Ray(new Point(1,1,0), new Vector(1,1,1))).size(), "wrong number of intersections - only part of the geometries is being intersected");

        // =============== Boundary Values Tests ==================
        // TC11: only one geometry is intersecting
        assertEquals(1, geometries.findIntsersections(new Ray(new Point(5,5,4), new Vector(1,1,1))).size(), "wrong number of intersections - only one geometry is intersecting");

        // TC12: no geometry is intersecting
        assertNull(geometries.findIntsersections(new Ray(new Point(7,7,6), new Vector(1,1,1))), "wrong number of intersections - no geometry is intersecting");

        // TC13: all the geometries are intersecting
        assertEquals(4, new Geometries(new Sphere(new Point(4,4,4), 3), new Plane(new Point(1,0,0), new Point(0,1,0), new Point(0,0,1)), new Geometries(new Polygon(new Point(1,4,4), new Point(4,1,4), new Point(4,4,1)))).findIntsersections(new Ray(new Point(0,0,-1), new Vector(1,1,1))).size(), "wrong number of intersections - all the geometries are intersecting");

        // TC12: empty list
        assertNull(new Geometries().findIntsersections(new Ray(new Point(7,7,6), new Vector(1,1,1))), "wrong number of intersections - empty list");

    }
}
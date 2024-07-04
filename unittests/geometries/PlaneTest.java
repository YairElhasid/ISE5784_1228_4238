package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * tests for plane
 * @author Sagiv Maoz and Yair Elhasid
 */
class PlaneTest {

    private final Plane plane = new Plane(new Point(1,0,0), new Vector(1 / Math.sqrt(3), 1 / Math.sqrt(3), 1 / Math.sqrt(3)));
    private final Plane plane2 = new Plane(new Point(2,0,0), new Point(0,2,0), new Point(0,0,2));
    private final Plane plane3 = new Plane(new Point(2,0,2), new Point(0,2,2), new Point(2,2,2));


    /** Test method for {@link geometries.Plane#Plane(Point, Point, Point)}. */
    @Test
    void test3PointsConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC00: checks the calculation of the normal
        assertTrue(new Vector(1 / Math.sqrt(3), 1 / Math.sqrt(3), 1 / Math.sqrt(3)).equals(new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)).getNormal()) || new Vector(-1 / Math.sqrt(3), -1 / Math.sqrt(3), -1 / Math.sqrt(3)).equals(new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)).getNormal()) , "failed constructor");
        // TC01: checks that the constructor do not throws an exception on normal case
        assertDoesNotThrow(() -> new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)), "throwing exception in full constructor for some reason");
        // TC02: checks that the normal's length is 1
        assertEquals(new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)).getNormal().length(),1,  "normal's length is not 1");

        // =============== Boundary Values Tests ==================
        // TC10: case that 2 out of 3 points are the same
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 0, 0), new Point(1, 0, 0), new Point(0, 0, 1)), "same points at constructor do not throws exception");
        // TC11: case that the 3 points are on the same ray
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 0, 0), new Point(2, 0, 0), new Point(3, 0, 0)), "the points are in the same line in the constructor but it is not throwing exception");
    }

    /** Test method for {@link geometries.Plane#Plane(Point, primitives.Vector)}. */
    @Test
    void testPointVectorConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: just check that the second constructor do not throws an exception
        assertDoesNotThrow(() -> new Plane(new Point(1, 0, 0), new Vector(1, 1, 1)), "throwing exception in normal constructor for some reason");
        // TC02: check for correct normalized normal
        assertTrue(new Vector(1 / Math.sqrt(3), 1 / Math.sqrt(3), 1 / Math.sqrt(3)).equals(new Plane(new Point(1, 0, 0), new Vector(1, 1, 1)).getNormal()) || new Vector(-1 / Math.sqrt(3), -1 / Math.sqrt(3), -1 / Math.sqrt(3)).equals(new Plane(new Point(1, 0, 0), new Vector(1, 1, 1)).getNormal()), "failed normalization on normal constructor");
    }

    /** Test method for {@link geometries.Plane#getNormal(Point)}. */
    @Test
    void testGetNormalP() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: check the normal returned
        assertTrue(new Vector(1 / Math.sqrt(3), 1 / Math.sqrt(3), 1 / Math.sqrt(3)).equals(plane.getNormal(new Point(1,0,0))) || new Vector(-1 / Math.sqrt(3), -1 / Math.sqrt(3), -1 / Math.sqrt(3)).equals(plane.getNormal(new Point(1,0,0))) , "wrong normal returned");
    }

    /** Test method for {@link geometries.Plane#getNormal()}. */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: check the normal returned
        assertTrue(new Vector(1 / Math.sqrt(3), 1 / Math.sqrt(3), 1 / Math.sqrt(3)).equals(plane.getNormal()) || new Vector(-1 / Math.sqrt(3), -1 / Math.sqrt(3), -1 / Math.sqrt(3)).equals(plane.getNormal()) , "wrong normal returned");
    }

    /** Test method for {@link geometries.Plane#findIntsersections(Ray)}. */
    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        // TC00: the ray is intersecting the plane (1 point)
        assertEquals(List.of(new Point(2,0,0)), plane2.findIntsersections(new Ray(new Point(3,1, 0), new Vector(-1,-1,0))), "wrong intersection, regular ray intersects the plane");

        // TC01: the ray is not intersecting the plane (0 points)
        assertNull(plane2.findIntsersections(new Ray(new Point(1,-1, 0), new Vector(-1,-1,0))), "wrong intersection, regular ray does not intersects the plane");

        // =============== Boundary Values Tests ==================
        // **** Group: the ray is parallel to the plane
        //TC11: the ray is on the plane (0 point)
        assertNull(plane2.findIntsersections(new Ray(new Point(2,1, -1), new Vector(0,1, -1))), "wrong intersection, parallel ray on the plane");

        //TC12: the ray is not on the plane (0 points)
        assertNull(plane2.findIntsersections(new Ray(new Point(4,4,4), new Vector(0,1, -1))), "wrong intersection, parallel ray is not on the plane");

        // **** Group: the ray is vertical to the plane
        // TC13: the ray is starting before the plane (1 point)
        assertEquals(List.of(new Point(2,0,0)), plane2.findIntsersections(new Ray(new Point(3,1, 1), new Vector(-1,-1,-1))), "wrong intersection, orthogonal ray intersects the plane");

        // TC14: the ray is starting after the plane (0 points)
        assertNull(plane2.findIntsersections(new Ray(new Point(1,-1, -1), new Vector(-1,-1,-1))), "wrong intersection, orthogonal ray does not intersects the plane");

        // TC15: the ray is starting on the plane (0 points)
        assertNull(plane2.findIntsersections(new Ray(new Point(2,0, 0), new Vector(-1,-1,-1))), "wrong intersection, orthogonal ray starts on the plane");

        // **** Group: the ray is not parallel and not vertical to the plane
        //TC16: the ray is starting at the plane (0 points)
        assertNull(plane2.findIntsersections(new Ray(new Point(1,1,0), new Vector(-8,-8,-4))), "wrong intersection - starts at the plane");
        //TC17: the ray is starting at the point that representing the plane (0 points)
        assertNull(plane2.findIntsersections(new Ray(new Point(2,0,0), new Vector(-8,-8,0))), "wrong intersection - starts at the plane");

    }

    /** Test method for {@link geometries.Plane#findGeoIntsersectionsHelper(Ray, double)}. */
    @Test
    void testFindIntersectionsDistance() {
        // ============ Equivalence Partitions Tests ==============
        // TC00: the intersection point is before the distance
        assertEquals(List.of(new Intersectable.GeoPoint(plane3, new Point(0, 0, 2))), plane3.findGeoIntsersectionsHelper(new Ray(new Point(0,0,0), new Vector(0,0,1)), 3), "incorrect intersection with bounded distance: 3");

        // TC01: the intersection point is after the distance
        assertNull(plane3.findGeoIntsersectionsHelper(new Ray(new Point(0,0,0), new Vector(0,0, 1)), 1), "incorrect intersection with bounded distance: 1");

        // =============== Boundary Values Tests ==================
        // TC10: the intersection point is on the distance
        assertEquals(List.of(new Intersectable.GeoPoint(plane3, new Point(0, 0, 2))), plane3.findGeoIntsersectionsHelper(new Ray(new Point(0,0,0), new Vector(0,0,1)), 2), "incorrect intersection with bounded distance: 2");

    }
}

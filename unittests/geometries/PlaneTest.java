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

    private Plane plane = new Plane(new Point(1,0,0), new Vector(1 / Math.sqrt(3), 1 / Math.sqrt(3), 1 / Math.sqrt(3)));


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

    /** Test method for {@link geometries.Plane#getNormal()}. */
    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        // TC00: the ray is intersecting the plane
        assertEquals(List.of(new Point(4, 7, 7)), sphere2.findIntsersections(new Ray(new Point(2,5,5), new Vector(1,1,1))), "wrong intersection, regular intersection");
        // TC01: the ray is not intersecting the plane

        // =============== Boundary Values Tests ==================
        // **** Group: the ray is parallel to the plane
        //TC11: the ray is on the plane
        //TC12: the ray is not on the plane

        // **** Group: the ray is vertical to the plane
        // TC13: the ray is starting before the plane
        // TC14: the ray is starting after the plane
        // TC15: the ray is starting on the plane

        // **** Group: the ray is not parallel and not vertical to the plane
        //TC16: the ray is starting at the plane
        //TC17: the ray is starting at the point that representing the plane

    }
}

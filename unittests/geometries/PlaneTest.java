package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    Plane plane = new Plane(new Point(1,0,0), new Vector(1 / Math.sqrt(3), 1 / Math.sqrt(3), 1 / Math.sqrt(3)));


    /** Test method for {@link geometries.Plane#Plane(Point, Point, Point)}. */
    @Test
    void test3PointsConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC00: checks the calculation of the normal
        assertTrue(new Vector(1 / Math.sqrt(3), 1 / Math.sqrt(3), 1 / Math.sqrt(3)).equals(new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)).getNormal()) || new Vector(-1 / Math.sqrt(3), -1 / Math.sqrt(3), -1 / Math.sqrt(3)).equals(new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)).getNormal()) , "failed constructor");
        // TC01: checks that the constructor do not throws an exception on normal case
        assertDoesNotThrow(() -> new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)), "throwing exception for some reason");
        // TC02: checks that the normal's length is 1
        assertEquals(new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)).getNormal().length(),1,  "normal's length is not 1");

        // =============== Boundary Values Tests ==================
        // TC10: case that 2 out of 3 points are the same
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 0, 0), new Point(1, 0, 0), new Point(0, 0, 1)), "same points at constructor do not throws exception");
        // TC11: case that the 3 points are on the same ray
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 0, 0), new Point(2, 0, 0), new Point(3, 0, 1)), "the points are in the same line in the constructor but it is not throwing exception");
    }

    /** Test method for {@link geometries.Plane#Plane(Point, primitives.Vector)}. */
    @Test
    void testPointVectorConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // No assert statements to document

        // =============== Boundary Values Tests ==================
        // No assert statements to document
    }

    /** Test method for {@link geometries.Plane#getNormal(Point)}. */
    @Test
    void testGetNormalP() {
        // ============ Equivalence Partitions Tests ==============
        // No assert statements to document

        // =============== Boundary Values Tests ==================
        // No assert statements to document
    }

    /** Test method for {@link geometries.Plane#getNormal()}. */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // No assert statements to document

        // =============== Boundary Values Tests ==================
        // No assert statements to document
    }
}

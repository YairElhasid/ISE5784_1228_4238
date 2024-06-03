package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * tests for triangle
 * @author Sagiv Maoz and Yair Elhasid
 */
class TriangleTest {

    private final Triangle triangle = new Triangle(new Point(2,0,0), new Point(0,2,0), new Point(0,0,2));

    /** Test method for {@link geometries.Triangle#getNormal(Point)}. */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC00: some point on the triangle
        assertTrue(new Vector(1 / Math.sqrt(3), 1 / Math.sqrt(3), 1 / Math.sqrt(3)).equals(triangle.getNormal(new Point(0.2, 0.3, 0.5))) || new Vector(1 / Math.sqrt(3), 1 / Math.sqrt(3), 1 / Math.sqrt(3)).equals(triangle.getNormal(new Point(0.2, 0.3, 0.5))), "failed normal on bottom base");
    }

    /** Test method for {@link geometries.Triangle#findIntsersections(Ray)}. */
    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        // TC00: the intersection point with the plane is inside the triangle (1 point)
        assertEquals(List.of(new Point(2/3.0, 2/3.0, 2/3.0)), triangle.findIntsersections(new Ray(new Point(0.5, 0.5, 0.5), new Vector(1,1,1))), "wrong intersection, regular ray on the triangle");

        // TC01: the intersection point with the plane is outside the triangle and parallel to edge (0 points)
        assertNull(triangle.findIntsersections(new Ray(new Point(1,-1,2), new Vector(1,1,1))), "wrong intersections - point on the plane is not in the triangle");

        // TC02: the intersection point with the plane is outside the triangle and parallel to vertex (0 points)
        assertNull(triangle.findIntsersections(new Ray(new Point(-4, -4, 0.5), new Vector(1,1,1))), "wrong intersections - point on the plane is not in the triangle");

        // =============== Boundary Values Tests ==================
        // TC10: the intersection point with the plane is on edge of the triangle (0 points)
        assertNull(triangle.findIntsersections(new Ray(new Point(1, -1, 1), new Vector(0, 2, -1))), "wrong intersections - point on the plane is on edge of the the triangle");

        // TC11: the intersection point with the plane is on vertex of the triangle (0 points)
        assertNull(triangle.findIntsersections(new Ray(new Point(3, 1, 1), new Vector(0, 2, -1))), "wrong intersections - point on the plane is on vertex of the the triangle");

        // TC12: the intersection point with the plane is on continuation of an edge of the triangle (0 points)
        assertNull(triangle.findIntsersections(new Ray(new Point(-2,-1,2), new Vector(1,1,1))), "wrong intersections - point on the plane is on continuation of one of the edges of the triangle");

    }
}

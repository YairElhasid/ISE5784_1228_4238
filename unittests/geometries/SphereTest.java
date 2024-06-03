package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

/**
 * tests for sphere
 * @author Sagiv Maoz and Yair Elhasid
 */
class SphereTest {

    private final Sphere sphere1 = new Sphere(new Point(2, 3, 4), 5);
    private final Sphere sphere2 = new Sphere(new Point(5, 5, 5), 3);

    /** Test method for {@link geometries.Sphere#getNormal(Point)}. */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC00: some point on the sphere
        assertTrue(new Vector(0, 0.6, 0.8).equals(sphere1.getNormal(new Point(2, 6, 8))) || new Vector(0, -0.6, -0.8).equals(sphere1.getNormal(new Point(2, 6, 8))), "failed normal for the sphere");

    }

    /** Test method for {@link geometries.Sphere#findIntsersections(Ray)}. */
    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere2.findIntsersections(new Ray(new Point(8,8,8), new Vector(-1,-1,-1))), "wrong intersection, regular ray that not supposed to intersect the sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        assertEquals(List.of(new Point(2.44, 3.88, 3.88), new Point(4,7,7)), sphere2.findIntsersections(new Ray(new Point(1,1,1), new Vector(2,3,3))), "wrong intersection, regular ray");

        // TC03: Ray starts inside the sphere (1 point)
        assertEquals(List.of(new Point(4, 7, 7)), sphere2.findIntsersections(new Ray(new Point(2,5,5), new Vector(1,1,1))), "wrong intersection, regular ray on the sphere");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere2.findIntsersections(new Ray(new Point(4,9,5), new Vector(-1,1,0))), "wrong intersection, regular ray start after the sphere");


        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 point)
        assertEquals(List.of(new Point(4, 7, 7)), sphere2.findIntsersections(new Ray(new Point(2,5,5), new Vector(1,1,1))), "wrong intersection, regular ray on the sphere and going inside");

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere2.findIntsersections(new Ray(new Point(2,5,5), new Vector(-1,-1,-1))), "wrong intersection, regular ray on the sphere and going outside");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        assertEquals(List.of(new Point(2, 5, 5),new Point(8, 5, 5)), sphere2.findIntsersections(new Ray(new Point(1,5,5), new Vector(1,0,0))), "wrong intersection, regular ray goes through the center of the sphere and starts before it");

        // TC14: Ray starts at sphere and goes inside (1 point)
        assertEquals(List.of(new Point(8, 5, 5)), sphere2.findIntsersections(new Ray(new Point(2,5,5), new Vector(1,0,0))), "wrong intersection, regular ray goes through the center of the sphere and starts on the sphere towards him");

        // TC15: Ray starts inside (1 point)
        assertEquals(List.of(new Point(8, 5, 5)), sphere2.findIntsersections(new Ray(new Point(3,5,5), new Vector(1,0,0))), "wrong intersection, regular ray goes through the center of the sphere and starts inside the sphere towards him");

        // TC16: Ray starts at the center (1 point)
        assertEquals(List.of(new Point(8, 5, 5)), sphere2.findIntsersections(new Ray(new Point(5,5,5), new Vector(1,0,0))), "wrong intersection, regular ray starts on the center of the sphere");

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere2.findIntsersections(new Ray(new Point(2,5,5), new Vector(-1,0,0))), "wrong intersection, regular ray goes through the center of the sphere and starts on the sphere and goes outside");

        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere2.findIntsersections(new Ray(new Point(1,5,5), new Vector(-1,0,0))), "wrong intersection, regular ray goes through the center of the sphere and starts after the sphere");


        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(sphere2.findIntsersections(new Ray(new Point(8,3,3), new Vector(0,1,1))), "wrong intersection, tangent ray starts before the sphere");

        // TC20: Ray starts at the tangent point
        assertNull(sphere2.findIntsersections(new Ray(new Point(8,5,5), new Vector(0,1,1))), "wrong intersection, tangent ray starts on the sphere");

        // TC21: Ray starts after the tangent point
        assertNull(sphere2.findIntsersections(new Ray(new Point(8,6,6), new Vector(0,1,1))), "wrong intersection, tangent ray starts after the sphere");

        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line (0 points)
        assertNull(sphere2.findIntsersections(new Ray(new Point(10,5,5), new Vector(0,1,1))), "wrong intersection, orthogonal ray outside the sphere");

        // TC23: Ray's line is inside, ray is orthogonal to ray start to sphere's center line (1 point)
        assertEquals(List.of(new Point(6,7,7)), sphere2.findIntsersections(new Ray(new Point(6,5,5), new Vector(0,1,1))), "wrong intersection, orthogonal ray outside the sphere");

    }
}

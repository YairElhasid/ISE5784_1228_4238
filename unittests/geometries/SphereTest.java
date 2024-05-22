package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    Sphere sphere = new Sphere(new Point(2, 3, 4), 5);

    /** Test method for {@link geometries.Sphere#getNormal(Point)}. */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC00: some point on the sphere
        assertTrue(new Vector(0, 0.6, 0.8).equals(sphere.getNormal(new Point(2, 6, 8))) || new Vector(0, -0.6, -0.8).equals(sphere.getNormal(new Point(2, 6, 8))), "failed normal for the sphere");

    }
}

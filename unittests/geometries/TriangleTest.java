package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * tests for triangle
 * @author Sagiv Maoz and Yair Elhasid
 */
class TriangleTest {

    private Triangle triangle = new Triangle(new Point(1,0,0), new Point(0,1,0), new Point(0,0,1));

    /** Test method for {@link geometries.Triangle#getNormal(Point)}. */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC00: some point on the triangle
        assertTrue(new Vector(1 / Math.sqrt(3), 1 / Math.sqrt(3), 1 / Math.sqrt(3)).equals(triangle.getNormal(new Point(0.2, 0.3, 0.5))) || new Vector(1 / Math.sqrt(3), 1 / Math.sqrt(3), 1 / Math.sqrt(3)).equals(triangle.getNormal(new Point(0.2, 0.3, 0.5))), "failed normal on bottom base");
    }
}

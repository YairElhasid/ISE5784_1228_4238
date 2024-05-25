package primitives;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class representing unit tests for the Point class.
 * @author Sagiv Maoz and Yair Elhasid
 */
class PointTest {

    private Point p1 = new Point(1, 2, 3);
    private Point p2 = new Point(2, 4, 6);
    private Point p3 = new Point(2, 4, 5);

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC00: subtract two points
        assertEquals(new Vector(1, 2, 3), p2.subtract(p1), "Incorrect subtraction");
        // TC01:check that normal subtraction do nor throws anything
        assertDoesNotThrow(() -> p1.subtract(p2), "failed subtraction");

        // =============== Boundary Values Tests ==================
        // TC10: subtraction gives zero vector
        assertThrows(IllegalArgumentException.class ,() -> p2.subtract(new Point(p2.xyz)), "did not subtract to zero");
    }

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC00: adds two points
        assertEquals(new Point(3, 6, 9), p1.add(new Vector(p2.xyz)), "Incorrect add");

        // =============== Boundary Values Tests ==================
        // TC10: in case that the add will bring 0 point
        assertEquals(Point.ZERO, p2.add(new Vector(-2, -4, -6)), "did not add to zero");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC00: squared distance between 2 points
        assertEquals(14, p2.distanceSquared(p1), "wrong squared distance");

        // =============== Boundary Values Tests ==================
        // TC10: when the squared distance is 0
        assertEquals(0, p2.distance(p2), "not zero distance");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        // TC00: distance between 2 points
        assertEquals(Math.sqrt(14), p2.distance(p1), "wrong distance");

        // =============== Boundary Values Tests ==================
        // TC10: when the distance is 0
        assertEquals(0, p2.distanceSquared(p2), "not zero distance");
    }
}

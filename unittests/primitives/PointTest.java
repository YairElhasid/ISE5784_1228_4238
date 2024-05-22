package primitives;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Class representing unit tests for the Point class.
 *
 * @author Yair Elhasid and Sagiv Maoz
 */
class PointTest {

    Point  p1         = new Point(1, 2, 3);
    Point  p2         = new Point(2, 4, 6);
    Point  p3         = new Point(2, 4, 5);

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============
            assertEquals(new Vector(1, 2, 3), p1.subtract(p2), "Incorrect subtraction");

        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class ,() -> p2.subtract(new Point(p2.xyz)), "did not subtract to zero");
    }

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(3, 6, 9), p1.add(new Vector(p2.xyz)), "Incorrect add");
        // =============== Boundary Values Tests ==================
        assertEquals(Point.ZERO, p2.subtract(new Point(-2, -4, -6)), "did not add to zero");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
     */
    @Test
    void distanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(14 , p2.distance(p1), "wrong squared distance");
        // =============== Boundary Values Tests ==================
        assertEquals(0 , p2.distance(p2), "not zero distance");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */
    @Test
    void distance() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(Math.sqrt(14) , p2.distanceSquared(p1), "wrong distance");
        // =============== Boundary Values Tests ==================
        assertEquals(0 , p2.distanceSquared(p2), "not zero distance");
    }

    /**
     * Test method for {@link primitives.Point#equals(Object)}.
     */
    @Test
    void testEquals() {
        // ============ Equivalence Partitions Tests ==============

        // =============== Boundary Values Tests ==================
    }
}
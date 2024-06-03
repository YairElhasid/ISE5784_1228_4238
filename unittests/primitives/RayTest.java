package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class representing unit tests for the Ray class.
 * @author Sagiv Maoz and Yair Elhasid
 */
class RayTest {

    private final Ray ray = new Ray(new Point(1,1,1), new Vector(1,0,0));

    /**
     * Test method for {@link primitives.Ray#getPoint(double)}.
     */
    @Test
    void getPoint() {
        // ============ Equivalence Partitions Tests ==============
        // TC00: t positive
        assertEquals(new Point(2,1,1), ray.getPoint(1), "wrong point  - positive t");
        // TC01: t negative
        assertEquals(new Point(0,1,1), ray.getPoint(-1), "wrong point  - negative t");


        // =============== Boundary Values Tests ==================
        // TC10: t = 0
        assertEquals(new Point(1,1,1), ray.getPoint(0), "wrong point  - t = 0");

    }
}
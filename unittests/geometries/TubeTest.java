package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    Tube tube = new Tube(5, new Ray(new Point(2, 3, 4), new Vector(1, 0, 0)));

    /**
     * Test method for {@link geometries.Tube#getNormal(Point)}
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC00: normal at the shell of the cylinder
        assertTrue(new Vector(0, 1, 0).equals(tube.getNormal(new Point(7, 3, 4))) || new Vector(0, -1, 0).equals(tube.getNormal(new Point(7, 3, 4))), "failed normal on shell");

        // =============== Boundary Values Tests ==================
        // TC10: normal for point that creates 90 degrees with the start of the axis
        assertTrue(new Vector(0, 1, 0).equals(tube.getNormal(new Point(2, 3, 9))) || new Vector(0, -1, 0).equals(tube.getNormal(new Point(2, 3, 9))), "failed normal on shell when the point is doing 90 degrees with the axis point");
    }
}

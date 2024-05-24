package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    Cylinder cylinder = new Cylinder(5, new Ray(new Point(2, 3, 4), new Vector(1,0,0)), 10);

    /** Test method for {@link geometries.Cylinder#getNormal(Point)}. */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC00: normal at the bottom base of the cylinder
        assertTrue(new Vector(1, 0, 0).equals(cylinder.getNormal(new Point(2, 3, 7))) || new Vector(-1, 0, 0).equals(cylinder.getNormal(new Point(2, 3, 7))), "failed normal on bottom base");
        // TC01: normal at the top base of the cylinder
        assertTrue(new Vector(1, 0, 0).equals(cylinder.getNormal(new Point(12, 3, 7))) || new Vector(-1, 0, 0).equals(cylinder.getNormal(new Point(12, 3, 7))), "failed normal on top base");
        // TC02: normal at the shell of the cylinder
        assertTrue(new Vector(0, 1, 0).equals(cylinder.getNormal(new Point(7, 8, 4))) || new Vector(0, -1, 0).equals(cylinder.getNormal(new Point(7, 8, 4))), "failed normal on shell");

        // =============== Boundary Values Tests ==================
        // TC10: normal at the center of the top base of the cylinder
        assertTrue(new Vector(1, 0, 0).equals(cylinder.getNormal(new Point(12, 3, 4))) || new Vector(-1, 0, 0).equals(cylinder.getNormal(new Point(12, 3, 4))), "failed normal on center of top base");
        // TC11: normal at the center of the bottom base of the cylinder
        assertTrue(new Vector(1, 0, 0).equals(cylinder.getNormal(new Point(2, 3, 4))) || new Vector(-1, 0, 0).equals(cylinder.getNormal(new Point(2, 3, 4))), "failed normal on center of bottom base");
    }
}

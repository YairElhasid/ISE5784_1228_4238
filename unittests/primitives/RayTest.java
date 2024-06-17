package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
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

    /**
     * Test method for {@link primitives.Ray#findClosestPoint(List<Point>)}.
     */
    @Test
    void getfindClosestPoint() {
        List<Point> points1 = new LinkedList<>();
        points1.add(new Point(2,1,1));
        points1.add(new Point(3,1,1));
        points1.add(new Point(4,1,1));
        points1.add(new Point(5,1,1));

        List<Point> points2 = new LinkedList<>();
        points2.add(new Point(3,1,1));
        points2.add(new Point(4,1,1));
        points2.add(new Point(5,1,1));
        points2.add(new Point(2,1,1));

        List<Point> points3 = new LinkedList<>();
        points3.add(new Point(3,1,1));
        points3.add(new Point(2,1,1));
        points3.add(new Point(5,1,1));
        points3.add(new Point(4,1,1));

        // ============ Equivalence Partitions Tests ==============
        // TC00:
        assertEquals(new Point(2,1,1),ray.findClosestPoint(points3),"wrong closest point - should be the second point");
        // =============== Boundary Values Tests ==================
        // TC10: empty list
        assertNull(ray.findClosestPoint(new LinkedList<Point>()),"wrong closest point - should be null");
        // TC11: first point is the closest
        assertEquals(new Point(2,1,1), ray.findClosestPoint(points1),"wrong closest point - should be the first point");
        // TC12:
        assertEquals(new Point(2,1,1), ray.findClosestPoint(points2),"wrong closest point - should be the last point");


    }
}
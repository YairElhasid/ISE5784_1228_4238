package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Testing Polygons
 * @author Dan
 */
public class PolygonTest {
   /**
    * Delta value for accuracy when comparing the numbers of type 'double' in
    * assertEquals
    */
   private final double DELTA = 0.000001;
   private Polygon p = new Polygon(new Point(0, 0, 1),new Point(1, 0, 0),new Point(0, 1, 0),new Point(-1, 1, 1));
   private final Polygon p2 = new Polygon(new Point(1,1,2), new Point(1, -1, 2),new Point(-1,-1,2),  new Point(-1,1,2));

   /** Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}. */
   @Test
   public void testConstructor() {
      // ============ Equivalence Partitions Tests ==============

      // TC01: Correct concave quadrangular with vertices in correct order
      assertDoesNotThrow(() -> new Polygon(new Point(0, 0, 1),
                                           new Point(1, 0, 0),
                                           new Point(0, 1, 0),
                                           new Point(-1, 1, 1)),
                         "Failed constructing a correct polygon");

      // TC02: Wrong vertices order
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0), new Point(-1, 1, 1)), //
                   "Constructed a polygon with wrong order of vertices");

      // TC03: Not in the same plane
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 2, 2)), //
                   "Constructed a polygon with vertices that are not in the same plane");

      // TC04: Concave quadrangular
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                                     new Point(0.5, 0.25, 0.5)), //
                   "Constructed a concave polygon");

      // =============== Boundary Values Tests ==================

      // TC10: Vertex on a side of a quadrangular
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                                     new Point(0, 0.5, 0.5)),
                   "Constructed a polygon with vertix on a side");

      // TC11: Last point = first point
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                   "Constructed a polygon with vertice on a side");

      // TC12: Co-located points
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
                   "Constructed a polygon with vertice on a side");

   }

   /** Test method for {@link geometries.Polygon#getNormal(primitives.Point)}. */
   @Test
   public void testGetNormal() {
      // ============ Equivalence Partitions Tests ==============
      // TC01: There is a simple single test here - using a quad
      Point[] pts =
         { new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1) };
      Polygon pol = new Polygon(pts);
      // ensure there are no exceptions
      assertDoesNotThrow(() -> pol.getNormal(new Point(0, 0, 1)), "");
      // generate the test result
      Vector result = pol.getNormal(new Point(0, 0, 1));
      // ensure |result| = 1
      assertEquals(1, result.length(), DELTA, "Polygon's normal is not a unit vector");
      // ensure the result is orthogonal to all the edges
      for (int i = 0; i < 3; ++i)
         assertEquals(0d, result.dotProduct(pts[i].subtract(pts[i == 0 ? 3 : i - 1])), DELTA,
                      "Polygon's normal is not orthogonal to one of the edges");
   }

   /** Test method for {@link geometries.Polygon#getNormal(primitives.Point)}. */
   @Test
   void testFindIntersections() {
      // ============ Equivalence Partitions Tests ==============
      // TC00: the intersection point with the plane is inside the polygon (1 point)
      assertEquals(List.of(new Point(0.2,0.2,0.6)), p.findIntsersections(new Ray(new Point(1,1,1),new Vector(new Double3(-0.8,-0.8,-0.4)))), "should return one point");
      // TC01: the intersection point with the plane is outside the polygon and parallel to edge (0 points)
      assertNull(p.findIntsersections(new Ray(new Point(1,1,1),new Vector(new Double3(1,0,-1)))), "should be zero intersection points when the intersection point with the plane is outside the polygon and parallel to edge");
      // TC02: the intersection point with the plane is outside the polygon and parallel to vertex (0 points)
      assertNull(p.findIntsersections(new Ray(new Point(1,1,1),new Vector(new Double3(-1,2,-3)))), "should be zero intersection points when the intersection point with the plane is outside the polygon and parallel to vertex");

      // =============== Boundary Values Tests ==================
      // TC10: the intersection point with the plane is on edge of the polygon (0 points)
      assertNull(p.findIntsersections(new Ray(new Point(0,0,0),new Vector(new Double3(1,0,1)))), "should be zero intersection points when the intersection point with the plane is on edge of the polygon");
      // TC11: the intersection point with the plane is on vertex of the polygon (0 points)
      assertNull(p.findIntsersections(new Ray(new Point(1,1,1),new Vector(new Double3(1,0,-1)))), "should be zero intersection points when the intersection point with the plane is on vertex of the polygon");
      // TC12: the intersection point with the plane is on continuation of an edge of the polygon (0 points)
      assertNull(p.findIntsersections(new Ray(new Point(0.5,-1,0.5),new Vector(new Double3(0,1,0)))), "should be zero intersection points when the intersection point with the plane is on continuation of an edge of the polygon");
   }

   /** Test method for {@link geometries.Polygon#findGeoIntsersectionsHelper(Ray, double)}. */
   @Test
   void testFindIntersectionsDistance() {
      // ============ Equivalence Partitions Tests ==============
      // TC00: the intersection point is before the distance
      assertEquals(List.of(new Intersectable.GeoPoint(p2, new Point(0, 0, 2))), p2.findGeoIntsersectionsHelper(new Ray(new Point(0,0,0), new Vector(0,0,1)), 3), "incorrect intersection with bounded distance: 3");

      // TC01: the intersection point is after the distance
      assertNull(p2.findGeoIntsersectionsHelper(new Ray(new Point(0,0,0), new Vector(0,0, 1)), 1), "incorrect intersection with bounded distance: 1");

      // =============== Boundary Values Tests ==================
      // TC10: the intersection point is on the distance
      assertEquals(List.of(new Intersectable.GeoPoint(p2, new Point(0, 0, 2))), p2.findGeoIntsersectionsHelper(new Ray(new Point(0,0,0), new Vector(0,0,1)), 2), "incorrect intersection with bounded distance: 2");

   }


}

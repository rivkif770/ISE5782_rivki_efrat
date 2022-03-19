package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class PlaneTest {

    /**
     * Test method for plane.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Proper normal examination
        Plane p1 = new Plane(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0));
        assertTrue(isZero(p1.getNormal().length() - 1),
                "Failed constructing a correct plan");
        // =============== Boundary Values Tests ==================

        // TC10: plan with two identical points
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 1), new Point(0, 1, 0)),
                "Constructed a plan with two identical points");

        // TC11: plane with points on it straight
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(1, 2, 1), new Point(1, 3, 1), new Point(1, 4, 1)),
                "Constructed a plane with points on it straight");

    }

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: .
        Plane pl = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point(0, 0, 1)), "Bad normal to plan");
    }
    /**
     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Plane plane = new Plane(new Point (1, 0, 0), new Point (1, 0, 0), new Point (1, 0, 0));

        // ============ Equivalence Partitions Tests ==============
        // The Ray's here ar not orthogonal and not parallels to the plane
        // TC01: Ray intersect the plane (1 point)
        Ray ray = new Ray(new Point(0,-2,0), new Vector(1, 4,-1));
        assertEquals(List.of(new Point(1,2,-1)), plane.findIntersections(ray));
        // TC02: Ray does not intersect the plane (0 point)
        ray = new Ray(new Point(0, 5, 0), new Vector(6,-5,0));
        assertNull(plane.findIntersections(ray));

        // =============== Boundary Values Tests ==================
        // **** Group: Ray is orthogonal to the plane
        // TC03: Ray start outside of the plane and goes inside the plane (1 point)
        ray = new Ray(new Point(-2, 0, 0), new Vector(2,1,2));
        assertEquals(List.of(new Point(-0.6666666666666667, 0.6666666666666666, 1.3333333333333333)),
                plane.findIntersections(ray));
        // TC04: Ray start outside of the plane (0 point)
        ray = new Ray(new Point(-2, 0, 0), new Vector(-1.53,-0.77,-1.53));
        assertNull(plane.findIntersections(ray));
        // TC05: Ray start inside the plane (0 point)
        ray = new Ray(new Point(0, 0, 1), new Vector(1,0.5,2));
        assertNull(plane.findIntersections(ray));
        // **** Group: Ray is parallel to the plane
        // TC06: Ray start inside (0 point)
        ray = new Ray(new Point(0.67, -2.16, 1.41), new Vector(-1.8,-0.56,2.08));
        assertNull(plane.findIntersections(ray));
        // TC07: Ray start outside (0 point)
        ray = new Ray(new Point(5, 0, 0), new Vector(-0.64,-0.2,0.74));
        assertNull(plane.findIntersections(ray));
    }
}
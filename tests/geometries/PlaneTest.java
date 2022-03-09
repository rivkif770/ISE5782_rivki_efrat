package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
}
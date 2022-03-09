package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class SphereTest {

//    /**
//     * Test method for sphere.
//     */
//    @Test
//    public void testConstructor() {
//        // ============ Equivalence Partitions Tests ==============
//
//        // TC01: Proper normal examination
//        Plane p1 = new Plane(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0));
//        assertTrue(isZero(p1.getNormal().length() - 1),
//                "Failed constructing a correct plan");
//        9
//    }
    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Proper normal examination
        Sphere sphere = new Sphere(new Point(0, 0, 0), 1);

        assertEquals(new Vector(0, 0, 1), sphere.getNormal(new Point(0, 0, 1)), "Bad normal to plan");
    }
}
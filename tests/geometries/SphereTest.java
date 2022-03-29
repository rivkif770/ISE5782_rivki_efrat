package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import java.util.List;

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
    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(new Point (1, 0, 0), 1);

        // ============ Equivalence Partitions Tests ==============

//        // TC01: Ray's line is outside the sphere (0 points)
//        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 2, 0))),
//                "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        Ray ray = new Ray(new Point(0.5,0,0), new Vector(1.5,0,0));
        assertEquals(List.of(new Point(2,0,0)), sphere.findIntersections(ray));

        // TC04: Ray starts after the sphere (0 points)
        ray = new Ray(new Point(3,0,0), new Vector(1,0,0));
        assertNull(sphere.findIntersections(ray));

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        ray = new Ray(new Point(0,0,0), new Vector(2,2,0));
        assertEquals(List.of(new Point(1,1,0)) ,sphere.findIntersections(ray));
        // TC12: Ray starts at sphere and goes outside (0 points)
        ray = new Ray(new Point(1,1,0), new Vector(2,2,0));
        assertNull(sphere.findIntersections(ray));

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        ray = new Ray(new Point(0,-2,0), new Vector(2,2,0));
        result = sphere.findIntersections(ray);
        assertEquals(2, result.size());
        // TC14: Ray starts at sphere and goes inside (1 points)
        ray = new Ray(new Point(2, 0, 0), new Vector(-1, 0, 0));
        assertEquals(List.of(new Point(0, 0, 0)), sphere.findIntersections(ray));
        // TC15: Ray starts inside (1 points)
        ray = new Ray(new Point(0.59, 0, 0), new Vector(-0.59, 0, 0));
        assertEquals(List.of(new Point(0, 0, 0)), sphere.findIntersections(ray));
        // TC16: Ray starts at the center (1 points)
        Ray ray1 = new Ray(new Point(1,0,0), new Vector(2,2,0));
        assertThrows(IllegalArgumentException.class, ()-> sphere.findIntersections(ray1));
        // TC17: Ray starts at sphere and goes outside (0 points)
        ray = new Ray(new Point(2,0,0), new Vector(2,0,0));
        assertNull(sphere.findIntersections(ray));
        // TC18: Ray starts after sphere (0 points)
        ray = new Ray(new Point(3,0,0), new Vector(1,0,0));
        assertNull(sphere.findIntersections(ray));

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        ray = new Ray(new Point(2,1,1), new Vector(-1,-1,0));
        assertNull(sphere.findIntersections(ray));
        // TC20: Ray starts at the tangent point
        ray = new Ray(new Point(1,0,1), new Vector(1,1,0));
        assertNull(sphere.findIntersections(ray));
        // TC21: Ray starts after the tangent point
        ray = new Ray(new Point(2,1,1), new Vector(4,4,0));
        assertNull(sphere.findIntersections(ray));

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        ray = new Ray(new Point(3,0,0), new Vector(0,0,1));
        assertNull(sphere.findIntersections(ray));

    }

}
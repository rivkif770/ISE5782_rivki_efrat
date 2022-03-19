package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: .There is a simple single test here
        Triangle triangle = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), triangle.getNormal(new Point(0, 0, 1)), "Bad normal to plan");
    }
    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Triangle t = new Triangle(new Point(2, 0, 0), new Point(0, 3, 0), new Point(0, 0, 0));

        // ============ Equivalence Partitions Tests ==============
        // The Ray's here ar not orthogonal and not parallels to the plane
        //TC01: Inside polygon/triangle(1 Point)
        Ray ray = new Ray(new Point(0, 0, -1), new Vector(new Point(1, 1, 1).getXyz()));
        List<Point> result = t.findIntersections(ray);
        Point p1 = new Point(1, 1, 0);
        assertEquals(List.of(p1), result, "Inside polygon/triangle(1 Point)");
        //TC02: Outside against edge(0 Point)
        ray = new Ray(new Point(0, 0, -1), new Vector(new Point(2, 1, 1).getXyz()));
        assertNull(t.findIntersections(ray), "Outside against edge");
        //TC03: Outside against vertex(0 Point)
        ray = new Ray(new Point(0, 0, -1), new Vector(new Point(3, -0.5, 1).getXyz()));
        assertNull(t.findIntersections(ray), "Outside against vertex");

        // =============== Boundary Values Tests ==================
        // //****Three cases (the ray begins "before" the plane)**
        //TC04: On edge(0 Point)
        ray = new Ray(new Point(0, 0, -1), new Vector(new Point(0, 1, 1).getXyz()));
        assertNull(t.findIntersections(ray), "On edge");
        //TC05: In vertex(0 Point)
        ray = new Ray(new Point(0, 0, -1), new Vector(new Point(0, 3, 1).getXyz()));
        assertNull(t.findIntersections(ray), "In vertex");
        //TC06: On edge's continuation(0 Point)
        Ray ray6 = new Ray(new Point(0, 0, -1), new Vector(new Point(0, 4, 1).getXyz()));
        assertNull(t.findIntersections(ray6), "On edge's continuation");
    }
}
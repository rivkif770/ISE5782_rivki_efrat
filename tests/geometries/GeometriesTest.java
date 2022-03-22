package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;


import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {
    Geometries geo = new Geometries(
            new Sphere(new Point(1, 0.5, 1), 2),
            new Plane(
                    new Point(-2,0,0),
                    new Point(0,0,4),
                    new Point(0,-2,0)),
            new Triangle(
                    new Point(1,0,0),
                    new Point(0.1, 0.5, 2.5),
                    new Point(-2, 0,0)));

    /**
     *tests for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        //TC01 ray intersect some of the geometries
        Ray ray = new Ray(new Point(0,-10,5), new Vector(1,10.5,-4));
        assertEquals(3, geo.findIntersections(ray).size());

        // =============== Boundary Values Tests ==================
        //TC02 ray intersect all the geometries
        ray = new Ray(new Point(0,-10,5), new Vector(0,10,-4));
        assertEquals(4, geo.findIntersections(ray).size());

        //TC03 ray intersect only one of the geometries
        ray = new Ray(new Point(0,-10,5), new Vector(0,10,-1));
        assertEquals(1, geo.findIntersections(ray).size());

        //TC04 ray not intersect the geometries
        ray = new Ray(new Point(6,2,2), new Vector(0,3,1));
        assertNull(geo.findIntersections(ray));

        //TC04 Empty body collection
        ray = new Ray(new Point(6,2,2), new Vector(0,3,1));
        assertNull(new Geometries().findIntersections(ray));
    }
}
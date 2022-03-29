package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void findClosestPoint() {
        Ray ray = new Ray(new Point(2,-2,3), new Vector(-2,-2,-2));
        /**
         * {@link primitives.Ray#findClosestPoint(List<Point>)}
         */
        // ============ Equivalence Partitions Tests ==============
        //TC01: Closest point is in the middle of the list
        var points = List.of(
                new Point(0,0,1),
                new Point(0,-1,0),
                new Point(1,-2,3),
                new Point(1,1,1),
                new Point(1,2,3));
            assertEquals(points.get(points.size() / 2), ray.findClosestPoint(points));

        // =============== Boundary Values Tests ==================
        //TC02: gets empty list
        points = null;
        assertNull(ray.findClosestPoint(points));

        //TC03: Closest point is in the beginning of the list
        points = List.of(
                new Point(1,-2,3),
                new Point(0,-1,0),
                new Point(1,-2,3),
                new Point(1,1,1),
                new Point(1,2,3));
        assertEquals(points.get(0), ray.findClosestPoint(points));

        //TC04: Closest point is in the end of the list
        points = List.of(
                new Point(1,-2,3),
                new Point(0,-1,0),
                new Point(1,-2,3),
                new Point(1,1,1),
                new Point(1,-2,3));
        assertEquals(points.get(points.size()-1), ray.findClosestPoint(points));
    }
}
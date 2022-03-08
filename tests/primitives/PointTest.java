package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class PointTest {

    @Test
    void testAdd() {
        Point p1 = new Point(1, 2, 3);

        // =============== Boundary Values Tests ==================
        // TC01: Checking the correct connection between the locator point
        assertTrue(p1.add(new Vector(2, 3, 4)).equals(new Point(3, 5, 7)), "ERROR: Point + Vector does not work correctly");
    }

    @Test
    void testSubtract() {
        Point p1 = new Point(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Proper Point subtraction test
        assertTrue(new Vector(1, 1, 1).equals(new Point(2, 3, 4).subtract(p1)), "ERROR: Point - Point does not work correctly");

    }

    @Test
    void testDistanceSquared() {
        Point p1 = new Point(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Checking the correctness of the Distance Squared between points
        assertTrue(isZero(p1.distanceSquared(new Point(2, 3, 4)) - 3), "ERROR: DistanceSquared() wrong value");
    }

    @Test
    void testDistance() {
        Point p1 = new Point(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Checking the correctness of the Distance between points
        assertTrue(isZero(p1.distance(new Point(1, 2, 8))- 5), "ERROR: Distance() wrong value");
    }
}
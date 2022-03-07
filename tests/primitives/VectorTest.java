package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

import static primitives.Util.*;

class VectorTest {

    @Test
    void testAdd() {
    }

    @Test
    void testSubtract() {
    }

    @Test
    void testScale() {
    }

    @Test
    void testDotProduct() {
    }

    @Test
    void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3), "crossProduct() for parallel vectors does not throw an exception");
    }

    @Test
    void testLengthSquared() {
    }

    @Test
    void testLength() {
    }

    @Test
    void testNormalize() {
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        if (!isZero(u.length() - 1))
            out.println("ERROR: the normalized vector is not a unit vector");
        try { // test that the vectors are co-lined
            v.crossProduct(u);
            out.println("ERROR: the normalized vector is not parallel to the original one");
        } catch (Exception e) {
        }
        if (v.dotProduct(u) < 0)
            out.println("ERROR: the normalized vector is opposite to the original one");
    }
}
package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(v1.length() * v2.length(), vr.length(), "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        //assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        //assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v2)));

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
    }
}
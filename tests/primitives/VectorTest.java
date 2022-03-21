package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

import static primitives.Util.*;

class VectorTest {

    @Test
    void testAdd() {
        Vector v1 = new Vector(1, 2, 3);

        // =============== Boundary Values Tests ==================
        // TC01: Test that Checking the connection of zero vector vectors
        assertTrue(v1.add(new Vector(-1, -2, -4)).equals(new Point(0, 0, -1)), "ERROR: Vector + Vector does not work correctly");
        //assertTrue(new Vector(0, 0, -1).equals(new Point(-1, -2, -4).add(v1)), "ERROR: Vector + Vector does not work correctly");

    }

    @Test
    void testSubtract() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Proper vector subtraction test
        assertTrue(new Vector(1, 1, 1).equals(new Point(2, 3, 4).subtract(v1)), "ERROR: Vector - Vector does not work correctly");

    }

    @Test
    void testDotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Normal vector scalar multiplication test
        assertTrue(isZero(v1.dotProduct(v2) + 28), "ERROR: dotProduct() wrong value");

        // =============== Boundary Values Tests ==================
        // TC01: Scalar product examination of orthogonal vectors is zero
        assertTrue(isZero(v1.dotProduct(v3)), "ERROR: dotProduct() for orthogonal vectors is not zero");

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
    void testlengthSquared(){
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Checking the correctness of the vector length Squared
        assertTrue(isZero(v1.lengthSquared() - 14), "ERROR: lengthSquared() wrong value");
    }

    @Test
    void testLength() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Checking the correctness of the vector length
        assertTrue(isZero(new Vector(0, 3, 4).length() - 5), "ERROR: length() wrong value");

    }

    @Test
    void testNormalize() {
        Vector v = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Checking the correctness of the vector length
        Vector u = v.normalize();
        assertTrue(isZero(u.length() - 1), "ERROR: the normalized vector is not a unit vector");

        // =============== Boundary Values Tests ==================
        // TC11: normalized vector is not parallel to the original one
        assertThrows(IllegalArgumentException.class, ()-> v.crossProduct(u), "ERROR: the normalized vector is not parallel to the original one");
        // TC02: normalized vector is opposite to the original one
        assertTrue(v.dotProduct(u) > 0, "ERROR: the normalized vector is opposite to the original one");

    }
}
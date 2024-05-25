package primitives;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class representing unit tests for the Vector class.
 * @author Yair Elhasid and Sagiv Maoz
 */
class VectorTest {
    private Vector v1         = new Vector(1, 2, 3);
    private Vector v1Opposite = new Vector(-1, -2, -3);
    private Vector v2         = new Vector(-2, -4, -6);
    private Vector v3         = new Vector(0, 3, -2);
    private Vector v4         = new Vector(1, 2, 2);
    private Vector v5         = new Vector(1,1,2);
    private Vector v6         = new Vector(0,0,1);
    private final double DELTA = 0.000001;
    /**
     * Test method for {@link primitives.Vector#Vector(double,double,double)}.
     */
    @Test
    void testVectorDouble()
    {
        // ============ Equivalence Partitions Tests ==============
        // TC01: regular vector
        assertDoesNotThrow(()->new Vector(1,1,1),"Should create this regular vector");
        // =============== Boundary Values Tests ==================
        // TC01: zero vector
        assertThrows(IllegalArgumentException.class,
                ()-> new Vector(0,0,0),"Cannot create zero vector");
    }
    /**
     * Test method for {@link primitives.Vector#Vector(Double3)}.
     */
    @Test
    void testVectorDouble3()
    {
        // ============ Equivalence Partitions Tests ==============
        // TC01: regular vector
        assertDoesNotThrow(()->new Vector(new Double3(1,1,1)),"Should create this regular vector");
        // =============== Boundary Values Tests ==================
        // TC01: zero vector
        assertThrows(IllegalArgumentException.class,
                ()-> new Vector(new Double3(0,0,0)),"Cannot create zero vector");
    }
    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: add of non-negative vectors
        assertDoesNotThrow(() -> v1.add(v4), "Failed adding two regular vectors");
        assertEquals(new Vector(2, 4, 5), v1.add(v4), "Wrong outcome");
        // =============== Boundary Values Tests ==================
        // TC01: add to a vector his negative vector (+- itself)
        assertThrows(IllegalArgumentException.class,
                () -> v1.add(v1Opposite), "Should throw an vector zero exception");
    }
    /**
     * Test method for {@link primitives.Vector#subtract(Point)}.
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: subtract of different vectors
        assertDoesNotThrow(() -> v1.subtract(v4), "Failed adding two regular vectors");
        assertEquals(v6, v1.subtract(v4), "Wrong outcome");
        // =============== Boundary Values Tests ==================
        // TC01: subtract of the same vector
        assertThrows(IllegalArgumentException.class,
                () -> v1.subtract(v1), "Should throw an vector zero exception");

    }
    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: scale by positive number
        assertDoesNotThrow(()->v1.scale(2),"Failed scale a vector by positive number");
        assertEquals(new Vector(2, 4, 6), v1.scale(2), "Wrong outcome");
        // TC02: scale by negative number
        assertDoesNotThrow(()->v1.scale(-2),"Failed scale a vector by negative number");
        assertEquals(v2, v1.scale(-2), "Wrong outcome");
        // =============== Boundary Values Tests ==================
        // TC01: scale by zero
        assertThrows(IllegalArgumentException.class,
                ()-> v1.scale(0),"Should throw an vector zero exception");
    }
    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: sharp angle between the vectors
        assertDoesNotThrow(()->v1.dotProduct(v4),"Failed a regular dot product");
        assertEquals(11, v1.dotProduct(v4), "Wrong outcome");
        // TC02: Obtuse angle between the vectors
        assertDoesNotThrow(()->v3.dotProduct(v5),"Failed a regular dot product");
        assertEquals(-1, v3.dotProduct(v5), "Wrong outcome");
        // =============== Boundary Values Tests ==================
        // TC01: orthogonal vectors
        assertDoesNotThrow(()->v1.dotProduct(v3),"Failed an dot product between orthogonal vectors");
        assertEquals(0, v1.dotProduct(v3),"Wrong outcome");
        // TC02: one vector is normalized
        assertDoesNotThrow(()->v1.dotProduct(v6),"Failed an dot product with a normalized vector");
        assertEquals(3, v1.dotProduct(v6),"Wrong outcome");
    }
    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that length of cross-product is proper and doesn't throw exception
        assertDoesNotThrow(()->v1.crossProduct(v3),"Failed a regular cross product");
        Vector vr = v1.crossProduct(v3);
        assertEquals(v1.length() * v3.length(), vr.length(), DELTA, "crossProduct() wrong result length");
        // TC02: Test cross-product result orthogonality to its operands
        assertEquals(0, vr.dotProduct(v1), "crossProduct() result is not orthogonal to 1st operand");
        assertEquals(0, vr.dotProduct(v3), "crossProduct() result is not orthogonal to 2nd operand");
        // =============== Boundary Values Tests ==================
        // TC01: cross product with the same vector
        assertThrows(IllegalArgumentException.class,
                () -> v1.crossProduct(v1), "crossProduct() for the same vector does not throw an exception");
        // TC02: cross product with parallel vector
        assertThrows(IllegalArgumentException.class,
                () -> v1Opposite.crossProduct(v2), "crossProduct() for parallel vectors does not throw an exception");
        // TC03: cross product with the negative vector(+- itself)
        assertThrows(IllegalArgumentException.class,
                () -> v1Opposite.crossProduct(v1), "crossProduct() for negative vectors does not throw an exception");
        // TC04: cross product with the negative direction and a different length
        assertThrows(IllegalArgumentException.class,
                () -> v2.crossProduct(v1), "crossProduct() for negative directions vectors does not throw an exception");
    }
    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test the outcome is proper and not throw exception
        assertDoesNotThrow(()->v1.lengthSquared(),"Failed a regular lengthSquared");
        assertEquals( 14, v1.lengthSquared(), "Wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test the outcome is proper and not throw exception
        assertDoesNotThrow(()->v4.length(),"Failed a regular length calculation");
        assertEquals( 3, v4.length(), "Wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    void testNormalize() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test the outcome is proper and not throw exception
        assertDoesNotThrow(()->v1.normalize(),"Failed a regular length calculation");
        assertEquals( new Vector(1/Math.sqrt(14),2/Math.sqrt(14),3/Math.sqrt(14)), v1.normalize(), "Wrong result");
        // =============== Boundary Values Tests ==================
        // TC01: an normalized vector
        Vector tmp = new Vector(0,0,1);
        assertDoesNotThrow(()->v6.normalize(),"Failed a regular length calculation");
        assertEquals( new Vector(0,0,1), tmp.normalize(), "Wrong result");
    }
}
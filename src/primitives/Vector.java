package primitives;

/**
 * class of geometrical vector
 * @author Sagiv Maoz and Yair Elhasid
 */
public class Vector extends Point {
    /**
     * full constructor
     * @param x - x
     * @param y - y
     * @param z - z
     */
    public Vector(double x, double y, double z)  {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("zero vector");
    }

    /**
     * constructor that get a Double3 object
     * @param xyz - the Double3 object
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("zero vector");

    }

    /**
     * adds a new vector to ours
     * @param v - the other vector
     * @return the added vector
     */
    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }

    /**
     * scale the vector in scalar
     * @param scale - a number
     * @return the scaled vaetor
     */
    public Vector Scale(double scale) {
        return new Vector(xyz.scale(scale));
    }

    /**
     * dot product
     * @param v - the other vector
     * @return the product
     */
    public double dotProduct(Vector v) {
        return xyz.d1 * v.xyz.d1 + xyz.d2 * v.xyz.d2 + xyz.d3 * v.xyz.d3;
    }

    /**
     * cross product
     * @param v - the other vector
     * @return the product vector
     */
    public Vector crossProduct(Vector v) {
        return new Vector((xyz.d2 * v.xyz.d3) - (xyz.d3 * v.xyz.d2),
            (xyz.d3 * v.xyz.d1) - (xyz.d1 * v.xyz.d3),
            (xyz.d1 * v.xyz.d2) - (xyz.d2 * v.xyz.d1));
    }

    /**
     * calculates the squared length of the vector
     * @return the outcome
     */
    public double lengthSquared() {
        return this.dotProduct(this);
    }
    /**
     * calculates the length of the vector
     * @return the outcome
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * normalizes the vector
     * @return a normalize vector
     */
    public Vector normalize() {
        double len = this.length();
        return new Vector(xyz.d1 / len, xyz.d2 / len, xyz.d3 / len);
    }
    @Override
    public String toString() {
        return "vector's head: " + xyz.toString();
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

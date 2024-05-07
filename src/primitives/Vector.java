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
        if (xyz == Double3.ZERO)
            throw new IllegalArgumentException("zero vector");
    }
    /**
     * constructor that get a Double3 object
     * @param xyz - the Double3 object
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (xyz == Double3.ZERO)
            throw new IllegalArgumentException("zero vector");

    }
}

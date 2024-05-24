package geometries;
import primitives.*;

/**
 * the class implements cylinder shape
 */
public class Cylinder extends Tube {
    final private double height;

    /**
     * full constructor
     * @param radius - the radius
     * @param axis - the axis
     * @param height - the height
     */
    public Cylinder(double radius, Ray axis, double height) {
        super(radius, axis);
        if (height < 0)
            throw new IllegalArgumentException("Height cannot be negative");
        this.height = height;
    }

    @Override
    public Vector getNormal(Point p) {
        if (p.equals(axis.getHead())) {
            return (axis.getDirection());
        }
        double t = (axis.getDirection()).dotProduct(p.subtract(axis.getHead()));
        if (t == 0 || t == height){ //if the point is on the base, top or bottom:
            return (axis.getDirection());
        }
        return super.getNormal(p);
    }
}

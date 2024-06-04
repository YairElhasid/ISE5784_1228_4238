package geometries;
import primitives.*;

import java.util.List;

import static primitives.Util.isZero;

/**
 * the class implements infinite tube shape
 * @author Sagiv Maoz and Yair Elhasid
 */
public class Tube extends RadialGeometry {
    /**
     * the axis of the tube
     */
    final protected Ray axis;

    /**
     * full constructor
     * @param radius - the radius
     * @param axis - the axis
     */
    public Tube(double radius, Ray axis) {
       super(radius);
       this.axis = axis;
   }
    @Override
    public Vector getNormal(Point p) {
        Vector direction = axis.getDirection();
        Point head = axis.getHead();
        double t = direction.dotProduct(p.subtract(head));
        Point o = (isZero(t)) ? head : axis.getPoint(t);
        return p.subtract(o).normalize() ;
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }

}

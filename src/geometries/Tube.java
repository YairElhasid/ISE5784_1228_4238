package geometries;
import primitives.*;

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
        double t = axis.getDirection().dotProduct(p.subtract(axis.getHead()));
        Point O = (t == 0) ? axis.getHead() : axis.getHead().add(axis.getDirection().scale(t));
        return p.subtract(O) ;
    }

}

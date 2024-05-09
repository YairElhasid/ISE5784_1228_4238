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
        return  null;
    }

}

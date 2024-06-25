package lighting;

import primitives.*;
import primitives.Color;

/**
 * the class implements ambient light
 * @author Sagiv Maoz and Yair Elhasid
 */
public class AmbientLight extends Light {


    /**
     * the color black
     */
    public final static AmbientLight NONE = new AmbientLight(Color.BLACK, 0);

    /**
     * double 3 constructor
     * @param intensity - the color
     * @param Ka - the scaling double 3
     */
    public AmbientLight(primitives.Color intensity, Double3 Ka) {
        super(intensity.scale(Ka));
    }

    /**
     * double  constructor
     * @param intensity - the color
     * @param Ka - the scaling double
     */
    public AmbientLight(primitives.Color intensity, double Ka) {
        super(intensity.scale(Ka));
    }


}

package lighting;

import primitives.*;
import primitives.Color;

/**
 * the class implements ambient light
 * @author Sagiv Maoz and Yair Elhasid
 */
public class AmbientLight {

    final private  primitives.Color intensity;

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
        this.intensity = intensity.scale(Ka);
    }

    /**
     * double  constructor
     * @param intensity - the color
     * @param Ka - the scaling double
     */
    public AmbientLight(primitives.Color intensity, double Ka) {
        this.intensity = intensity.scale(Ka);
    }

    /**
     * returns the intensity of the light
     * @return - the intensity
     */
    public primitives.Color getIntensity(){
        return intensity;
    }

}

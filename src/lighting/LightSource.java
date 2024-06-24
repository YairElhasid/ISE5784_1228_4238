package lighting;

import primitives.*;

/**
 * interface for light source
 * @author Sagiv Maoz and Yair Elhasid
 */
public interface LightSource {
    /**
     * calculates the intensity at given point
     * @param p - the point
     * @return intensity at given point
     */
    public primitives.Color getIntensity(Point p);

    /**
     * calculates the vector from the light to a point
     * @param p - the point
     * @return - the vector
     */
    public Vector getL(Point p);

}

package lighting;

import primitives.*;

/**
 * class for directional light
 * @author Sagiv Maoz and Yair Elhasid
 */
public class DirectionalLight extends Light implements LightSource {
    private Vector direction;

    /**
     * parameters constructor
     * @param color - the intensity
     * @param direction - the direction
     */
    public DirectionalLight(primitives.Color color, Vector direction) {
        super(color);
        this.direction = direction;
    }


    @Override
    public Color getIntensity(Point p) {
        return intensity;
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }

    @Override
    public double getDistance(Point p) {
        return Double.POSITIVE_INFINITY;
    }
}

package lighting;

/**
 * abstract class for light
 * @author Sagiv Maoz and Yair Elhasid
 */
abstract class Light {
    protected primitives.Color intensity;

    /**
     * color constructor
     * @param intensity - the color
     */
    protected Light(primitives.Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getter for the intensity
     * @return - the intensity
     */
    public primitives.Color getIntensity() {
        return intensity;
    }
}

package lighting;

import primitives.*;

import static primitives.Util.isZero;

/**
 * class for point light
 * @author Sagiv Maoz and Yair Elhasid
 */
public class PointLight extends Light implements LightSource{

    protected Point position;
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

    /**
     * parameters constructor
     * @param position - the position
     * @param color - the intensity
     */
    public PointLight(Point position, primitives.Color color) {
        super(color);
        this.position = position;
    }

    /**
     * set the kC
     * @param kC - the new kC
     * @return - this Point Light - for concatenation
     */
    public PointLight setKC(double kC) {
        if(kC < 0 || kC > 1){
            throw new IllegalArgumentException("kC must be between 0 and 1");
        }
        this.kC = kC;
        return this;
    }

    /**
     * set the kL
     * @param kL - the new kL
     * @return - this Point Light - for concatenation
     */
    public PointLight setKL(double kL) {
        if(kL< 0 || kL> 1){
            throw new IllegalArgumentException("kL must be between 0 and 1");
        }
        this.kL = kL;
        return this;
    }

    /**
     * set the kQ
     * @param kQ - the new kQ
     * @return - this Point Light - for concatenation
     */
    public PointLight setKQ(double kQ) {
        if(kQ < 0 || kQ > 1){
            throw new IllegalArgumentException("kQ must be between 0 and 1");
        }
        this.kQ = kQ;
        return this;
    }

    /**
     * helping method for the get intensity method (DRY)
     * @param p the point
     * @return the denominator
     */
    protected double getDenominator(Point p){
        double d = p.distance(position);
        return kC + (d * kL) + (d * d * kQ);
    }

    @Override
    public Color getIntensity(Point p) {
        double denominator = getDenominator(p);
        if(isZero(denominator)){
            throw new IllegalArgumentException("denominator cannot be zero");
        }
        return intensity.scale(1/denominator);
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position);
    }

    @Override
    public double getDistance(Point p) {
        return position.distance(p);
    }

}

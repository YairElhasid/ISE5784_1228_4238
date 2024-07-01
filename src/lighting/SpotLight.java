package lighting;

import primitives.*;

import static primitives.Util.isZero;

/**
 * class for spot light
 * @author Sagiv Maoz and Yair Elhasid
 */
public class SpotLight extends PointLight{

    private Vector direction;
    //for the bonus of narrowed beam - the bigger, the narrowed
    private double kN = 1;

    /**
     * parameters constructor
     * @param position - the position
     * @param color - the intensity
     * @param direction - the direction
     */
    public SpotLight(Point position, primitives.Color color, Vector direction) {
        super(position, color);
        this.direction = direction;
    }

    /**
     * parameters constructor with the narrowed beam parameter
     * @param position - the position
     * @param color - the intensity
     * @param direction - the direction
     * @param kN - the direction
     */
    public SpotLight(Point position, primitives.Color color, Vector direction, double kN ) {
        super(position, color);
        if(kN < 0 || isZero(kN)) throw new IllegalArgumentException("kN must be greater than zero");
        this.kN = kN;
        this.direction = direction;
    }

    @Override
    public SpotLight setKC(double kC) {
        super.setKC(kC);
        return this;
    }

    @Override
    public SpotLight setKL(double kL) {
        super.setKL(kL);
        return this;
    }

    @Override
    public SpotLight setKQ(double kQ) {
        super.setKQ(kQ);
        return this;
    }

    /**
     * set the kN
     * @param kN - the new kN
     * @return - this Spot Light - for concatenation
     */
    public SpotLight setKN(double kN) {
        if(kN < 0 || isZero(kN)) throw new IllegalArgumentException("kN must be greater than zero");
        this.kN = kN;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double denominator = getDenominator(p);
        double cosAngle = direction.normalize().dotProduct(getL(p).normalize());
        if(isZero(cosAngle) || cosAngle < 0){
            return primitives.Color.BLACK;
        }
        if(isZero(denominator)){
            throw new IllegalArgumentException("denominator cannot be zero");
        }
        return intensity.scale(Math.pow(cosAngle, kN) / denominator);
    }


}

package lighting;

import primitives.*;

import static primitives.Util.isZero;

/**
 * class for spot light
 * @author Sagiv Maoz and Yair Elhasid
 */
public class SpotLight extends PointLight{

    private Vector direction;

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
        return intensity.scale(cosAngle / denominator);
    }


}

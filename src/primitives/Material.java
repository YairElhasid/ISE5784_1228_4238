package primitives;

import lighting.PointLight;

public class Material {

    /**
     * diffusivity parameter
     */
    public Double3 kD = Double3.ZERO;
    /**
     * specular parameter
     */
    public Double3 kS = Double3.ZERO;
    /**
     * transparency parameter
     */
    public Double3 kT = Double3.ZERO;
    /**
     * reflective parameter
     */
    public Double3 kR = Double3.ZERO;
    /**
     * Shininess percentage
     */
    public int nShininess = 0;

    /**
     * set the kD
     * @param kD - the new kD
     * @return - this Material - for concatenation
     */
    public Material setKD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * set the kD
     * @param d the new value
     * @return - this Material - for concatenation
     */
    public Material setKD(double d) {
        this.kD = new Double3(d);
        return this;
    }

    /**
     * set the kS
     * @param kS - the new kS
     * @return - this Material - for concatenation
     */
    public Material setKS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * set the kS
     * @param d
     * @return - this Material - for concatenation
     */
    public Material setKS(double d) {
        this.kS = new Double3(d);
        return this;
    }

    /**
     * set the kR
     * @param kR - the new kR
     * @return - this Material - for concatenation
     */
    public Material setKR(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * set the kR
     * @param d the new value
     * @return - this Material - for concatenation
     */
    public Material setKR(double d) {
        this.kR = new Double3(d);
        return this;
    }

    /**
     * set the kT
     * @param kT - the new kT
     * @return - this Material - for concatenation
     */
    public Material setKT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * set the kT
     * @param d the new value
     * @return - this Material - for concatenation
     */
    public Material setKT(double d) {
        this.kT = new Double3(d);
        return this;
    }

    /**
     * set the nShininess
     * @param nShininess - the new nShininess
     * @return - this Material - for concatenation
     */
    public Material setNShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }


}

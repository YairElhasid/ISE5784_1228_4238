package geometries;

import primitives.*;

/**
 * abstract class for geometrical shapes
 * @author Sagiv Maoz and Yair Elhasid
 */
public  abstract class Geometry extends Intersectable {

    protected primitives.Color emission = primitives.Color.BLACK;
    private Material material = new Material();

    /**
     * calculate the normal vector to a shape in a certain point
     * @param p a point on the shape
     * @return the normal
     */
    public abstract Vector getNormal(Point p);

    /**
     * getter to the emission field
     * @return the emission light
     */
    public primitives.Color getEmission() {
        return emission;
    }

    /**
     * getter to the material field
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * setter o the emission field
     * @param emission the emission
     * @return this geometry - for concatenation
     */
    public Geometry setEmission(primitives.Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * setter for the material field
     * @param material the material
     * @return this geometry - for concatenation
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
}
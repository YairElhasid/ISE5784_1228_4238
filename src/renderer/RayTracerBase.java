package renderer;

import primitives.*;
import scene.Scene;


/**
 * class for the base of all the ray tracers we will create in our project
 * @author Sagiv Maoz and Yair Elhasid
 */
public abstract class RayTracerBase {
    /**
     * the scene
     */
    protected Scene scene;

    /**
     * scene builder
     * @param scene - the scene
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * gets the color of given gay
     * @param ray - the given ray
     * @return - the color of the ray's pixel
     */
    public abstract primitives.Color traceRay(Ray ray);
}

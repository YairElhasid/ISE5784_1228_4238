package renderer;

import geometries.Intersectable.GeoPoint;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * class for the simple ray tracer
 * @author Sagiv Maoz and Yair Elhasid
 */
public class SimpleRayTracer extends RayTracerBase{

    /**
     * scene builder
     * @param scene - the scene
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }
    /**
     * intersect the ray with the scene's geometries and return the closet point's color
     * @param ray - the current ray
     */
    public primitives.Color traceRay(Ray ray){
        List<GeoPoint> intersections = scene.geometries.findGeoIntsersections(ray);
        if (intersections == null){
            return scene.background;
        }
        return calcColor(ray.findClosestGeoPoint(intersections));
    }
    /**
     * return the color of a intersection point
     */
    private primitives.Color calcColor(GeoPoint gp){
        return scene.ambientLight.getIntensity()
                .add(gp.geometry.getEmission());
    }



}

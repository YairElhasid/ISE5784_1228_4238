package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import primitives.Ray;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

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
        return calcColor(ray.findClosestGeoPoint(intersections), ray);
    }
    /**
     * return the color of a intersection point
     */
    private primitives.Color calcColor(GeoPoint gp, Ray ray){
        primitives.Color res = primitives.Color.BLACK;
        return scene.ambientLight.getIntensity()
                .add(calcLocalEffects(gp, ray));
    }

    private primitives.Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDirection();
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return primitives.Color.BLACK;

        Material material = gp.geometry.getMaterial();
        Color color = gp.geometry.getEmission();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl *nv> 0) { // sign(nl) == sing(nv)
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(
                        iL.scale(calcDiffusive(material, nl)
                                .add(calcSpecular(material, n, 1, nl, v))));

            }

            return color;

        }

    }

}

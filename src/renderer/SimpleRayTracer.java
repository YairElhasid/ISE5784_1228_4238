package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.DirectionalLight;
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
public class SimpleRayTracer extends RayTracerBase {

    /**
     * when to stop the recursion of the reflection and transparency
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    /**
     * when to stop the recursion of the reflection and transparency
     */
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * constant for moving the point on a geometry in the direction of the normal
     */
    private static final double EPS = 0.01;

    /**
     * scene builder
     *
     * @param scene - the scene
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * intersect the ray with the scene's geometries and return the closet point's color
     *
     * @param ray - the current ray
     */
    public primitives.Color traceRay(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntsersections(ray);
        if (intersections == null) {
            return scene.background;
        }
        return calcColor(ray.findClosestGeoPoint(intersections), ray);
    }

    private Ray getReflectionRay(Ray ray,GeoPoint gp){
        Point p = gp.point;
        Vector n = gp.geometry.getNormal(p);
        return new Ray(p.add(n.dotProduct(ray.getDirection()) > 0 ? n.scale(EPS) : n.scale(-EPS)), ray.getDirection());
    }

    private Ray getTrancparencyRay(Ray ray, GeoPoint gp){
        Point p = gp.point;
        Vector n = gp.geometry.getNormal(p);
        Vector d = ray.getDirection();
        return new Ray(p.add(n.dotProduct(d) > 0 ? n.scale(-EPS) : n.scale(EPS)), d.subtract(n.scale(-2).scale(n.dotProduct(d))));
    }

    /**
     * calculates the first arguments we need to add before we call the recursion
     * @param gp the geo point of intersection
     * @param ray the ray of intersection
     * @return the final color of the pixel
     */
    private Color calcColor(GeoPoint gp, Ray ray)


    /**
     * return the color of a intersection point - recursive method
     */
    private primitives.Color calcColor(GeoPoint gp, Ray ray) {
        primitives.Color res = primitives.Color.BLACK;
        return scene.ambientLight.getIntensity()
                .add(calcLocalEffects(gp, ray));
    }

    private Double3 calcDiffusive(primitives.Material mat, double absnl) {
        return mat.kD.scale(absnl);
    }

    private Double3 calcSpecular(primitives.Material mat, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(2 * nl));
        double mnr = v.scale(-1).dotProduct(r);
        return mat.kS.scale(Math.pow(alignZero(mnr) > 0 ? mnr: 0, mat.nShininess));
    }

    private primitives.Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDirection();
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return primitives.Color.BLACK;

        Material material = gp.geometry.getMaterial();
        Color color = gp.geometry.getEmission();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point).normalize();
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0 && unshaded(gp, l,n, nl, lightSource)) { // sign(nl) == sing(nv) or if the point is shaded
                Color iL = lightSource.getIntensity(gp.point);
                Double3 x = calcDiffusive(material, Math.abs(nl)), y = calcSpecular(material, n, l, nl, v);
                color = color.add(iL.scale(
                        x.add(y)));

            }


        }
        return color;

    }

    /**
     * indicate if some other geometry is shading our point
     * @param gp  the point
     * @param l the vector from the light source to the point
     * @param n the normal vector for this geometry
     * @return if some other geometry is shading our point or not
     */

    private boolean unshaded(GeoPoint gp , Vector l, Vector n, double nl, LightSource lightSource) {
        Vector lightDirection = l.scale(-1); // from point to light source on k

        Vector epsVector = n.scale(nl > 0 ? -EPS : EPS);
        Point point = gp.point.add(epsVector);

        Ray ray = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntsersections(ray, lightSource.getDistance(gp.point));
        return intersections == null;

    }


}
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
     * when to stop the recursion of the reflection and transparency
     */
    private static final Double3 MIN_K = new Double3(0.001);
    /**
     * initial value for the recursion of the reflection and transparency
     */
    private static final Double3 INITIAL_K = Double3.ONE;




    /**
     * scene builder
     *
     * @param scene - the scene
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * finding the closest intersection point
     * @param ray the intresecting ray
     * @return the closest intersection point or null if there isn't
     */
    private GeoPoint findClosestIntersection(Ray ray){
        var geoList = scene.geometries.findGeoIntsersections(ray);
        return geoList == null ? null : ray.findClosestGeoPoint(geoList);
    }

    /**
     * intersect the ray with the scene's geometries and return the closet point's color
     * @param ray - the current ray
     */
    public primitives.Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    private Ray constructRefractedRay(Ray ray,GeoPoint gp){
        Point p = gp.point;
        Vector n = gp.geometry.getNormal(p);
        return new Ray(p, n.dotProduct(ray.getDirection()) > 0 ? n: n.scale(-1), ray.getDirection());
    }

    private Ray constructReflectedRay(Ray ray, GeoPoint gp){
        Point p = gp.point;
        Vector n = gp.geometry.getNormal(p);
        Vector d = ray.getDirection();
        return new Ray(p, n.dotProduct(d) > 0 ? n.scale(-1) : n, d.subtract(n.scale(2).scale(n.dotProduct(d))));
    }

    /**
     * calculates the first arguments we need to add before we call the recursion
     * @param gp the geo point of intersection
     * @param ray the ray of intersection
     * @return the final color of the pixel
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * return the color of an intersection point - recursive method
     */
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k)  {
        Color color = calcLocalEffects(gp, ray, k);
        return 1 == level ? color : color.add(calcGlobalEffects(gp, ray, level, k));


    }

    /**
     * calculates the diffusive part in the phong model
     * @param mat material
     * @param absnl |n * l|
     * @return the diffusive part
     */
    private Double3 calcDiffusive(primitives.Material mat, double absnl) {
        return mat.kD.scale(absnl);
    }

    /**
     * calculates the specular part in the phong model
     * @param mat
     * @param n
     * @param l
     * @param nl
     * @param v
     * @return
     */
    private Double3 calcSpecular(primitives.Material mat, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(2 * nl));
        double mnr = v.scale(-1).dotProduct(r);
        return mat.kS.scale(Math.pow(alignZero(mnr) > 0 ? mnr: 0, mat.nShininess));
    }

    private primitives.Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDirection();
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return primitives.Color.BLACK;

        Material material = gp.geometry.getMaterial();
        Color color = gp.geometry.getEmission();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point).normalize();
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Double3 ktr = transparency(gp, l, n, nv, lightSource);
                if(MIN_K.lowerThan(ktr.product(k))) {
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);
                    Double3 x = calcDiffusive(material, Math.abs(nl)), y = calcSpecular(material, n, l, nl, v);
                    color = color.add(iL.scale(
                            x.add(y)));
                }
            }
        }
        return color;
    }

    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Material material = gp.geometry.getMaterial();
        return calcGlobalEffect(constructRefractedRay( ray, gp), material.kT, level, k).add(calcGlobalEffect(constructReflectedRay(ray, gp), material.kR, level, k));
    }



    private Color calcGlobalEffect(Ray ray, Double3 kx, int level, Double3 k) {
        Double3 kkx = kx.product(k);
        if (kx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx))
                .scale(kx);
    }


    /**
     * indicate if some other geometry is shading our point
     * @param gp  the point
     * @param l the vector from the light source to the point
     * @param n the normal vector for this geometry
     * @return if some other geometry is shading our point or not
     */
    private Double3 transparency(GeoPoint gp , Vector l, Vector n, double nl, LightSource lightSource) {
        Vector lightDirection = l.scale(-1); // from point to light source on k
        Ray ray = new Ray(gp.point, n.scale(nl > 0 ? -1 : 1), lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntsersections(ray, lightSource.getDistance(gp.point));
        Double3 finalK =  Double3.ONE;
        if(intersections == null){
            return finalK;
        }

        for(GeoPoint intersection : intersections) {
            finalK = finalK.product(intersection.geometry.getMaterial().kT);
            if(finalK.lowerThan(MIN_K))return finalK; //then we won't see the color anyway
        }
        return finalK;
    }

}
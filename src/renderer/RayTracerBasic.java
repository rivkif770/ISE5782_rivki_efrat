package renderer;

import geometries.Geometry;
import geometries.Intersectable;
import primitives.*;
import lighting.*;
import java.lang.Math.*;
import geometries.Intersectable.GeoPoint;
import scene.Scene;
import static primitives.Util.*;

import java.util.List;

/**
 * Rey tracer
 * calculate the color ray-geometry intersection
 * @author rivki and efrat
 */
public class RayTracerBasic extends RayTracerBase {
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = new Double3(1d);

    /**
     * ctor - initializing the scene parameter
     * uses super ctor
     *
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Calculation of intensity of shadow on pixel's color with consideration of the transparency
     * @param light - Light source that effects the color and thus the shadow
     * @param l - Vector from light source to point
     * @param n - Normal to point
     * @param geopoint - The point of which the color is calculated
     * @return - The attenuation coefficient of light - determines the intensity of the shadow
     */
    private Double3 transparency(GeoPoint geopoint, LightSource light, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n); //build ray with delta
        double lightDistance = light.getDistance(geopoint.point);
        var intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return Double3.ONE; //no intersections
        Double3 ktr = new Double3(1d);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                ktr = ktr.product(gp.geometry.getMaterial().getkT()); //the more transparency the less shadow
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
            }
        }
        return ktr;
    }

    /**
     * According to the pong model
     * This model is additive in which we connect all the components that will eventually
     * make up an image with background colors, self-colors and texture colors.
     * @param geoPoint the geometry and the lighted point at him
     * @param ray the ray that goes out of the camera
     * @return the color at the point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * overload methode of {@link renderer.RayTracerBasic#calcColor(GeoPoint, Ray)}
     * @param geoPoint the geometry and the lighted point at him
     * @param ray the ray that goes out of the camera
     * @param level the level of the recursion
     * @param k Represents influencing factors of transparency and reflection
     * @return the color at the point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = geoPoint.geometry.getEmission()
                .add(calcLocalEffects(geoPoint, ray, k));

        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));
    }
    /**
     * Computer lighting effects as at a certain point on geometry
     * @param gp the geometry and the lighted point at him
     * @param ray the ray from the camera
     * @return the total color at the point including the specular and diffusive
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        Color color = Color.BLACK;
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point); //normal to point
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) //vectors orthogonal - no effect
            return color;
        Material material = gp.geometry.getMaterial();

        for (LightSource lightSource : scene.lighting) { //sum of all effects of all lights on scene
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Double3 ktr = transparency(gp, lightSource, l, n); //intensity of shadow
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);;
                    color = color.add(iL.scale(calcDiffusive(material, nl)), iL.scale(calcSpecular(material, n, l, v)));
                }
            }
        return color;
    }
    /**
     * Computer influences factors of transparency and reflection
     * @param gp the geometry and the lighted point at him
     * @param ray the ray that goes out of the camera
     * @param level the level of the recursion
     * @param k Represents influencing factors of transparency and reflection
     * @return the color with the transparency and reflection
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k){
        Color color = Color.BLACK;
        Material material = gp.geometry.getMaterial();
        Double3 kr = material.getkR();
        Double3 kkr = k.product(kr); //in each recursive iteration the impact of the reflection decreases
        Vector n = gp.geometry.getNormal(gp.point);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
            Ray reflectedRay = constructReflected(gp, ray);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint == null) return color.add(scene.background);
            color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
        }
        Double3 kt = material.getkT();
        Double3 kkt = k.product(kt); //in each recursive iteration the impact of the refraction decreases
        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
            Ray refractedRay = constructRefracted(gp, ray);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint == null) return color.add(scene.background);
            color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
        }
        return color;
    }

    /**
     * Produces a reflection bean that starts from
     * the point where the ray struck from the camera and goes diagonally to the point
     * @param geoPoint the point where the ray hit from the camera
     * @param ray the ray from the camera
     * @return a reflection ray
     */
    private Ray constructReflected(GeoPoint geoPoint, Ray ray) {
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(v.dotProduct(n));
        // r = v - 2*(v * n) * n
        Vector r = v.subtract(n.scale(2d * nv)).normalize();

        return new Ray(geoPoint.point, r, n); //use the constructor with the normal for moving the head a little
    }
    /**
     * Produces a transparency bean of rays that starts from
     * the point where the ray hit from the camera and
     * goes in the direction almost like the original beam
     * @param geoPoint the point where the ray hit from the camera
     * @param inRay the ray from the camera
     * @return transparency ray
     */
    private Ray constructRefracted(GeoPoint geoPoint, Ray inRay) {
        return new Ray(geoPoint.point, inRay.getDir(), geoPoint.geometry.getNormal(geoPoint.point));
    }
    /**
     * Calculation of specular light component
     * @param material - Attenuation coefficient for specular light component
     * @param n - normal to point
     * @param l - direction vector from light to point
     * @param v - direction of ray shooted to point
     * @return Color - the calculated color of specular light component
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, Vector v) {
        Vector r = l.subtract(n.scale(2*l.dotProduct(n))).normalize();
        return material.kS.scale(Math.pow(Math.max(0,v.scale(-1).dotProduct(r)), material.nShininess));
    }
    /**
     * Calculation of diffusion light component
     * @param material - normal to point
     * @param nl - dot product between n-normal to point and l-direction vector from light to point
     * @return Color - the calculated color of diffusion light component
     */
    private Double3 calcDiffusive(Material material,double nl) {
        return material.kD.scale(Math.abs(nl));
    }

    /**
     * Trace the ray and calculates the color of the point that interact with the geometries of the scene
     * @param ray the ray that came out of the camera
     * @return the color of the object that the ray is interact with
     */
    public Color TraceRay(Ray ray) {
        GeoPoint clossestGeoPoint = findClosestIntersection(ray);
        if (clossestGeoPoint == null)
            return scene.background;
        return calcColor(clossestGeoPoint, ray);
    }
    /**
     * find closest intersection to the starting point of the ray
     * @param ray the ray that intersect with the geometries of the scene
     * @return the GeoPoint that is point is the closest point to the starting point of the ray
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        if(ray == null){
            return null;
        }

        List<GeoPoint> points = scene.geometries.findGeoIntersections(ray);
        return ray.findClosestGeoPoint(points);
    }
}
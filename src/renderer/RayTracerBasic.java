package renderer;

import geometries.Geometry;
import geometries.Intersectable;
import primitives.*;
import lighting.*;
import java.lang.Math.*;
import geometries.Intersectable.GeoPoint;
import scene.Scene;
import static primitives.Util.alignZero;

import java.util.List;

/**
 * Rey tracer
 * calculate the color ray-geometry intersection
 * @author rivki and efrat
 */
public class RayTracerBasic extends RayTracerBase {
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * ctor - initializing the scene parameter
     * uses super ctor
     *
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    private static final double DELTA = 0.1;
    private boolean unshaded(GeoPoint gp,LightSource ls, Vector l, Vector n, double nv) {
        Vector lightDirection = l.scale(-1); //vector from the point to the light source

        Vector deltaVector=n.scale(nv<0?DELTA:-DELTA);
        Point p=gp.point.add(deltaVector);
        Ray lightRay = new Ray(p, lightDirection);

        double lightDistance = ls.getDistance(gp.point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        //if there are no intersections return true (there is no shadow)
        if (intersections == null) {
            return true;
        }

        //for each intersection
        for (GeoPoint intersection : intersections) {
            //if there are points in the intersectios list that are closer to the point
            //then light source, return false
            if (lightDistance > intersection.point.distance(gp.point)) {
                return false;
            }

        }
        return true;
    }

//        Vector lightDirection = l.scale(-1); // from point to light source
//        Vector epsVector = n.scale(nv < 0 ? DELTA : -DELTA);
//        Point point = gp.point.add(epsVector);
//        double lightDistance = light.getDistance(gp.point);
//        Ray lightRay = new Ray(point, lightDirection);
//        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
//        for (GeoPoint gps : intersections) {
//            if (alignZero(gps.point.distance(gp.point) -lightDistance ) <= 0) {
//                ktr *= gps.geometry.getMaterial().setkD(); //the more transparency the less shadow
//                if (ktr < MIN_CALC_COLOR_K) return 0.0;
//            }
//      return intersections== null;

    /**
     * According to the pong model
     * This model is additive in which we connect all the components that will eventually
     * make up an image with background colors, self-colors and texture colors.
     * @param geoPoint the geometry and the lighted point at him
     * @param ray the ray that goes out of the camera
     * @return the color at the point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, double k ){
        if(geoPoint == null){
            return scene.background;
        }
        Color color= scene.ambientLight.getIntensity().add(geoPoint.geometry.getEmission())
                .add(calcLocalEffects(geoPoint, ray));
        return 1 == level ? color : color = color.add(calcGlobalEffects(gp, ray, level, k));
    }
    /**
     * Computer lighting effects as at a certain point on geometry
     * @param intersection the geometry and the lighted point at him
     * @param ray the ray from the camera
     * @return the total color at the point including the specular and diffusive
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Color color = Color.BLACK;
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point); //normal to point
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) //vectors orthogonal - no effect
            return color;
        Material material = intersection.geometry.getMaterial();

        for (LightSource lightSource : scene.lighting) { //sum of all effects of all lights on scene
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                if (unshaded(intersection,lightSource, l,n,nv)) {
                    Color iL = lightSource.getIntensity(intersection.point);
                    color = color.add(iL.scale(calcDiffusive(material, nl)), iL.scale(calcSpecular(material, n, l, v)));
                }
            }
        }
        return color;
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
        GeoPoint clossestGeoPoint = ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
        if (clossestGeoPoint == null)
            return scene.background;
        return calcColor(clossestGeoPoint, ray);
    }

}
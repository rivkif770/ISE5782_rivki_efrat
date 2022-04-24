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
 * the RayTracerBasic implements the class RayTracerBase and implements the traceRay method.
 *
 */
public class RayTracerBasic extends RayTracerBase {


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
     * return the color of point
     * @param geoPoint
     * @return Color
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray ){
        if(geoPoint == null){
            return scene.background;
        }
        return scene.ambientLight.getIntensity().add(geoPoint.geometry.getEmission())
                .add(calcLocalEffects(geoPoint, ray));
    }
    /**
     * Computer lighting effects as at a certain point on geometry
     * @param intersection the geometry and the lighted point at him
     * @param ray the ray from the camera
     * @return the total color at the point including the specular and diffusive
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Color color = intersection.geometry.getEmission();
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
                Color iL = lightSource.getIntensity(intersection.point);
                color = color.add(iL.scale(calcDiffusive(material, nl)), iL.scale(calcSpecular(material, n, l, v)));
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
     * @param material -
     * @param nl -
     * @return Color - the calculated color of diffusion light component
     */
    private Double3 calcDiffusive(Material material,double nl) {
        return material.kD.scale(Math.abs(nl));
    }
    /**
     * implementation of super class trace ray method
     */
    public Color TraceRay(Ray ray) {
        GeoPoint clossestGeoPoint = ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
        if (clossestGeoPoint == null)
            return scene.background;
        return calcColor(clossestGeoPoint, ray);
    }

}
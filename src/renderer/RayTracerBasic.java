package renderer;

import geometries.Geometry;
import geometries.Intersectable;
import primitives.*;
import geometries.Intersectable.GeoPoint;
import scene.Scene;

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
     * @param geoPointpoint
     * @return Color
     */
    private Color calcColor(GeoPoint geoPointpoint, Ray ray ){
        if(geoPointpoint == null){
            return scene.background;
        }
        return scene.ambientLight.getIntensity().add(geoPointpoint.geometry.getEmission());
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
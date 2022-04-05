package renderer;

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
     * return the color of closestPoint
     * @param ray - the traced ray
     * @return Color
     */
    @Override
    public Color TraceRay(Ray ray) {
        List<Point> pointList = scene.geometries.findIntersections(ray);
        GeoPoint closestPoint = ray.findClosestPoint(pointList);
        return calcColor(closestPoint);
    }

    /**
     * return the color of point
     * @param geoPoint
     * @return Color
     */
    private Color calcColor(GeoPoint geoPoint ){
        if(geoPoint == null){
            return scene.background;
        }
        return scene.ambientLight.getIntensity().add(geoPoint.geometry.getEmission());
    }

}
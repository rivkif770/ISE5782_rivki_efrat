package renderer;

import static primitives.Util.*;
import elements.*;
import geometries.Intersectable.*;
import primitives.*;
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
     * @param _scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color TraceRay(Ray ray) {
        List<Point> pointList = scene.geometries.findIntersections(ray);
        Point closestPoint = ray.findClosestPoint(pointList);
        return calcColor(closestPoint);
    }

    private Color calcColor(Point point){
        if(point == null){
            return scene.background;
        }
        return scene.ambientLight.getIntensity();
    }
}
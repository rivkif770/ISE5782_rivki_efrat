package renderer;

import static primitives.Util.*;
import elements.*;
import geometries.Intersectable.*;
import primitives.*;
import scene.Scene;
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
        return null;
    }
}
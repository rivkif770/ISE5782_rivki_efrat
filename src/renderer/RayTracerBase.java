package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * abstract class to rey tracing
 * @author rivki and efrat
 */
public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * Constructor for the RayTracerBase base
     * @param scene the scene
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Trace the ray and calculates the color
     * @param ray the ray that came out of the camera
     * @return the color of the object that the ray is interact with
     */
    public abstract Color TraceRay(Ray ray);

    public abstract Color TraceRays(List<Ray> rays);
}
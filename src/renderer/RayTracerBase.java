package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
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

    /**
     * Trace the ray and calculates the color of the point that interact with the geometries of the scene
     * @param rays the ray that came out of the camera
     * @return the color of the object that the ray is interact with
     */
    public abstract Color TraceRays(List<Ray> rays);

    /**
     * Checks the color of the pixel with the help of individual rays and averages between
     * them and only if necessary continues to send beams of rays in recursion
     * @param centerP center pixl
     * @param Width Length
     * @param Height width
     * @param minWidth min Width
     * @param minHeight min Height
     * @param cameraLoc Camera location
     * @param Vright Vector right
     * @param Vup vector up
     * @param prePoints pre Points
     * @return Pixel color
     */
    public abstract Color AdaptiveSuperSamplingRec(Point centerP, double Width, double Height, double minWidth, double minHeight, Point cameraLoc, Vector Vright, Vector Vup, List<Point> prePoints);
}
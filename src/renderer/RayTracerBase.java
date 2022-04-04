package renderer;

        import primitives.Color;
        import primitives.Ray;
        import scene.Scene;

/**
 * the abstract RayTracerBase class is used to color a scene
 * with a given scene and a ray the class calculates the color of the ray
 *
 */
public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * ctor - initializing the scene parameter
     * @param scene1
     */
    public RayTracerBase(Scene scene1) {
        scene = scene1;
    }

    /**
     * the abstract method traces the ray to the point it hits in the scene,
     * it considers the different factors (ambient light, emmision light, light sources exet.)
     * @param ray - the traced ray
     * @return - the discovered color
     */
    public abstract Color TraceRay(Ray ray);
}
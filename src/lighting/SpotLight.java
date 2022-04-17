package lighting;

import primitives.*;

public class SpotLight extends PointLight{
    private Vector direction;

    /**
     * ctor for spot light object
     * @param intensity - intensity of point light source
     * @param position - location of point light object on scene
     * @param kC - the constant attenuation factor
     * @param kL - the linear attenuation factor
     * @param kQ - the square attenuation factor
     * @param direction - direction vector for the light of spot light object on scene
     */
    public SpotLight(Color intensity, Point position, double kC, double kL, double kQ, Vector direction) {
        super(intensity, position, kC, kL, kQ);
        this.direction = direction.normalize();
    }

    /**
     * Default ctor for point light object
     * attenuation factor get default values - 1,0,0
     * @param intensity - intensity of point light source
     * @param position - location of point light object on scene
     * @param direction - direction vector for the light of spot light object on scene
     */

    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }
}

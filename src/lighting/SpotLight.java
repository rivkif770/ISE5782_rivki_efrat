package lighting;

import primitives.*;

/**
 * this class represent a spot light with direction and intensity of the light
 * and also can make a flashlight by focusing the the light
 * @author efrat and rivki
 */
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

    /**
     * get the intensity of the light in relation to the distance from the point
     * Calculate the light beam by calculating
     * the angle between the spot direction and the point direction
     * @param p The point where the light strikes
     * @return the color of the point
     */
    @Override
    public Color getIntensity(Point p) {
        Vector l = getL(p); // direction to the point
        double angle = direction.dotProduct(l); // the angle between the spot direction and the point direction
        double factor =  angle > 0 ? angle : 0;

        return super.getIntensity(p).scale(factor);
    }
}

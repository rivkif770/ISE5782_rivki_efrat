package lighting;

import primitives.*;

/**
 * Directional Light with constant light intensity and direction
 * @author efrat and rivki
 */
public class DirectionalLight extends Light implements LightSource{
    private Vector direction;

    /**
     * Constructor to a directional light object
     * @param intensity Color - color parameter of directional light source
     * @param direction Vector - direction parameter of directional light source
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }
    /**
     * Implementation of getIntesity of interface LightSource
     */
    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    /**
     * Implementation of getL of interface LightSource
     * direction of directional light
     */
    @Override
    public Vector getL(Point p) {
        return direction;
    }
    /**
     * get the distance between the starting point of the light source to some point
     * @param point the point to calculate the distance from
     * @return the distance between light and the point
     */
    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }
}

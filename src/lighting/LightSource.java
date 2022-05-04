package lighting;

import primitives.*;

/**
 * interface for light sources in some scene
 * @author efrat and rivki
 */
public interface LightSource {
    /**
     * get Intensity in point
     * @param p
     * @return Color in point
     */
    public Color getIntensity(Point p);

    /**
     * Returns a vector coming out of the lighting source to the object
     * @param p
     * @return Vector
     */
    public Vector getL(Point p);
    double getDistance(Point point);

}

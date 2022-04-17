package lighting;

import primitives.*;

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
    @Override
    public Color getIntensity(Point p) {
        return null;
    }

    @Override
    public Vector getL(Point p) {
        return null;
    }
}

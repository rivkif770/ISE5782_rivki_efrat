package elements;

import primitives.Color;
import primitives.Double3;

/**
 * this class represent a ambient light
 *
 */
public class AmbientLight {
    private Color _intensity;
    /**
     * Constructor for AmbientLight
     * @param Ia Ia is the presentation of the intensity of the light in the pong model
     * @param Ka Ka is the presentation of the reduce factor of the light in the pong model
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        Ia.scale(Ka);
    }

    /**
     * Default constructor for AmbientLight
     */
    public AmbientLight(){
        _intensity=Color.BLACK;
    }
    /**
     * getter for the intensity
     * @return the light intensity
     */
    public Color getIntensity() {
        return _intensity;
    }
}
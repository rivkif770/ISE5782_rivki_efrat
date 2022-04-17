package lighting;

import primitives.Color;

abstract class Light {
    private Color intensity;

    /**
     * constructor of light
     * @param intensity
     */
    public Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getter of intensity
     * @return
     */
    public Color getIntensity() {
        return intensity;
    }
}

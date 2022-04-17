package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource{
    private Point position;
    private double kC, kL, kQ;

    /**
     * ctor for point light object
     * @param intensity - intensity of point light source
     * @param position - location of point light object on scene
     * @param kC - the constant attenuation factor
     * @param kL - the linear attenuation factor
     * @param kQ - the square attenuation factor
     */
    public PointLight(Color intensity, Point position, double kC, double kL, double kQ) {
        super(intensity);
        this.position = position;
        this.kC = kC;
        this.kL = kL;
        this.kQ = kQ;
    }

    /**
     * Default ctor for point light object
     * attenuation factor get default values - 1,0,0
     * @param intensity - intensity of point light source
     * @param position - location of point light object on scene
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
        this.kC = 1;
        this.kL = 0;
        this.kQ = 0;
    }

    @Override
    public Color getIntensity(Point p) {
        return null;
    }

    @Override
    public Vector getL(Point p) {
        return null;
    }

    //region Setters
    /**
     * set Position of point light
     * @param position
     * @return point light object
     */
    public PointLight setPosition(Point position) {
        this.position = position;
        return this;
    }

    /**
     * builder pattern set - set the kC parameter - the constant attenuation factor
     * @param kC - constant attenuation factor
     * @return - point light object
     */
    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * builder pattern set - set the kL parameter - the linear attenuation factor
     * @param kL - linear attenuation factor
     * @return - point light object
     */
    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * builder pattern set - set the kq parameter - the square attenuation factor
     * @param kQ - square attenuation factor
     * @return - point light object
     */
    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }
    //endregion
}

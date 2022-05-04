package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;
import static primitives.Util.isZero;

/**
 * this class represent a point light like normal lamp. <br/>
 * with point that represent the position of the lamp and <br/>
 * the intensity of the light that depends on some variables related to the pong model
 * @author efrat and rivki
 */
public class PointLight extends Light implements LightSource{
    private Point position;
    private Double3 kC, kL, kQ;

    /**
     * ctor for point light object
     * @param intensity - intensity of point light source
     * @param position - location of point light object on scene
     * @param kC - the constant attenuation factor
     * @param kL - the linear attenuation factor
     * @param kQ - the square attenuation factor
     */
    public PointLight(Color intensity, Point position, Double3 kC, Double3 kL, Double3 kQ) {
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
        this.kC = new Double3(1);
        this.kL = Double3.ZERO;
        this.kQ = Double3.ZERO;
    }
    //region getters
    /**
     * get the intensity of the light in relation to the distance from the point
     * @param p The point where the light strikes
     * @return the color of the point
     */
    @Override
    public Color getIntensity(Point p) {
        double dist = p.distance(position);

        if(dist <= 0){
            return getIntensity();
        }

        Double3 factor = (kC.add(kL.scale(dist))).add(kQ.scale(dist * dist));

        return getIntensity().reduce(factor);
    }

    /**
     * get the the direction of the light to the point where its strikes
     * @param p The point where the light strikes
     * @return the direction of the light to the point
     */
    @Override
    public Vector getL(Point p) {
        Vector dir = p.subtract(position);
        return dir.normalize();
    }
    //endregion
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
    public PointLight setkC(Double3 kC) {
        this.kC = kC;
        return this;
    }
    public PointLight setkC(double kC) {
        this.kC = new Double3(kC);
        return this;
    }
    /**
     * builder pattern set - set the kL parameter - the linear attenuation factor
     * @param kL - linear attenuation factor
     * @return - point light object
     */
    public PointLight setkL(Double3 kL) {
        this.kL = kL;
        return this;
    }
    public PointLight setkL(double kL) {
        this.kL = new Double3(kL);
        return this;
    }
    /**
     * builder pattern set - set the kq parameter - the square attenuation factor
     * @param kQ - square attenuation factor
     * @return - point light object
     */
    public PointLight setkQ(Double3 kQ) {
        this.kQ = kQ;
        return this;
    }
    public PointLight setkQ(double kQ) {
        this.kQ =new Double3(kQ);
        return this;
    }
    //endregion
    /**
     * get the distance between the starting point of the light source to some point
     * @param point the point to calculate the distance from
     * @return the distance between light and the point
     */
    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }

}

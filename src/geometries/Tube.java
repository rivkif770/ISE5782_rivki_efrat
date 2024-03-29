package geometries;
import primitives.*;
import java.util.List;
import static primitives.Util.*;

/**
 * this class represent an infinite Tube by radius and ray
 *
 * @author efrat and rivki
 */
public class Tube extends Geometry{
    protected Ray axisRay;
    protected double radius;

    /**
     * constructor
     * @param r radius of tube
     * @param ray ray of tube
     */
    public Tube(int r, Ray ray) {
        if(r == 0) throw  new IllegalArgumentException("Invalid radius");
        axisRay = ray;
        radius = r;
    }
    //region getters
    /**
     *get ray ot tube
     * @return ray
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     *get radius ot tube
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Returns normal to point
     * @param p point
     * @return normal
     */
    @Override
    public Vector getNormal(Point p) {

        Point p0 = axisRay.getP0();
        Vector v = axisRay.getDir();

        Vector p0_p = p.subtract(p0);
        double t = alignZero(p0_p.dotProduct(v));

        if(isZero(t)) return p0_p.normalize();

        Point o = p0.add((v.scale(t)));
        Vector o_p = p.subtract(o);
        return o_p.normalize();
    }
    //endregion

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelperHelper(Ray ray, double maxDistance) {return null;}
    /**
     * toString
     * @return string
     */
    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }
}

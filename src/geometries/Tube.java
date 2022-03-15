package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.*;


public class Tube implements Geometry{
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
    @Override
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }
}

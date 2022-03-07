package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

public class Tube implements Geometry{
    protected Ray axisRay;
    protected double radius;

    /**
     *
     * @return
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     *
     * @return
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Returns normal to point
     * @param p
     * @return normal
     */
    public Vector getNormal(Point p) {

        Point p0 = axisRay.getP0();
        Vector v = axisRay.getDir();

        Vector p0_p = p.subtract(p0);
        double t = Util.alignZero(p0_p.dotProduct(v));
        return null;
    }

    /**
     * toString
     * @return
     */
    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }
}

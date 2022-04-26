package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Objects;
/**
 * this a class represent a final Cylinder by radius, ray and height
 *
 * @author efrat and rivki
 */
public class Cylinder extends Tube {
    private double height;

    /**
     * constructor
     * @param height height of cylinder
     */
    public Cylinder(int r, Ray ray, double height) {
        super(r, ray);
        this.height = height;
    }

    /**
     * Returns normal to point
     * @param point
     * @return normal
     */
    public Vector getNormal(Point point) {
        Point p0 = axisRay.getP0();
        Vector N = axisRay.getDir();

        if(point.equals(p0)) return N.scale(-1);
        Vector p0_p = point.subtract(p0);
        // find the vector on the lower base
        if(p0_p.dotProduct(N) == 0){ // the vectors is orthogonal to each other
            return N.scale(-1);
        }

        // find the vector on the upper base
        Vector vN = N.scale(N.dotProduct(p0_p));
        if(p0_p.equals(vN)){
            return N;
        }
        else {
            Vector p0_p_vN = p0_p.subtract(vN);
            return p0_p_vN.length() == radius ? p0_p_vN.normalize() : N;
        }
    }

    /**
     * equals
     * @param o the Object
     * @return boolean value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cylinder cylinder = (Cylinder) o;
        return Double.compare(cylinder.height, height) == 0 && super.equals(o);
    }

    /**
     * toString
     * @return string
     */
    @Override
    public String toString() {
        return  "Cylinder: " + super.toString() +
                "\nheight: " + height;
    }
}

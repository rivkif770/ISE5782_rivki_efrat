package geometries;

import primitives.Point;
import primitives.Vector;

import java.util.Objects;

public class Plane implements Geometry {
    public Point p0;
    public Vector normal;

    /**
     * constructor plan
     * @param p0-A point in the plane
     * @param normal-Normal to the plane
     * Normalize the vector in a state that is not normalized
     */
    public Plane(Point p0, Vector normal) {
        this.p0 = p0;
        if(normal.length()==1)
            this.normal = normal;
        else
            normal.normalize();
    }

    /**
     * constructor plan
     * @param x-point1
     * @param y-point2
     * @param z-point3
     */
    public Plane(Point x,Point y,Point z){
        //normal = x.subtract(y).crossProduct(y.subtract(z));
        normal = null;
        p0 = x;
    }

    /**
     * geter point p0
     * @return p0-A point in the plane
     */
    public Point getP0() {
        return p0;
    }

    /**
     * geret vector normal
     * @return normal-Normal to the plane
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    /**
     *geret vector normal
     * @return normal-Normal to the plane
     * @param point in the plan
     */
    public Vector getNormal(Point point) {
        return normal;
    }
    @Override
    /**
     * equals-Compares two plan
     * @param o-The object for comparison
     * @return Boolean value Whether the objects are equal or not
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane)) return false;
        Plane plane = (Plane) o;
        return p0.equals(plane.p0) && normal.equals(plane.normal);
    }

    /**
     *toString
     * @return Threading the values
     */
    @Override
    public String toString() {
        return "Plane:\n"+"p0: " +p0.toString() +"\nnormal: " + normal.toString();
    }

}

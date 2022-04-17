package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.*;

public class Plane extends Geometry {
    public Point q0;
    public Vector normal;

    /**
     * constructor plan
     * @param p0-A point in the plane
     * @param normal-Normal to the plane
     * Normalize the vector in a state that is not normalized
     */
    public Plane(Point p0, Vector normal) {
        this.q0 = p0;
        this.normal = normal.normalize();
    }

    /**
     * constructor plan
     * @param p1 -point1
     * @param p2 -point2
     * @param p3 -point3
     */
    public Plane(Point p1,Point p2,Point p3){
        q0 = p1;
        if(p1.equals(p2) || p1.equals(p3) || p2.equals(p3))
            throw new IllegalArgumentException("Two of the points are identical");

        Vector v1 = p2.subtract(p1);  //vector from p1 towards p2
        Vector v2 = p3.subtract(p1);  //vector from p1 towards p3

        Vector n = v1.crossProduct(v2);
        if(v1.normalize().equals(v2.normalize()))
            throw new IllegalArgumentException("There is a linear dependence between the vectors");
        normal = n.normalize();
    }

    /**
     * geter point p0
     * @return p0-A point in the plane
     */
    public Point getQ0() {
        return q0;
    }

    /**
     * geret vector normal
     * @return normal-Normal to the plane
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     *geret vector normal
     * @return normal-Normal to the plane
     * @param point in the plan
     */
    @Override
    public Vector getNormal(Point point) {
        return normal;
    }

    /**
     * find intersections point with the plane
     * @param ray ray that cross the plane
     * @return list of intersection points that were found => p0 + tv
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        if(q0.equals(p0)){
            return null;
        }

        Vector n = normal;

        // t = n∙(q0 - p0) / n∙v
        // if t > 0 point as found

        Vector p0_q0 = q0.subtract(p0);
        double mone = alignZero(n.dotProduct(p0_q0));
        if (isZero(mone)){ // the starting point of the ray is inside the plane
            return null;
        }

        double nv = alignZero(n.dotProduct(v));
        if(isZero(nv)){ // the ray is vertical on the plane
            return null;
        }

        double t = alignZero(mone / nv);

        if(t > 0){
            return List.of(new GeoPoint(this,  ray.getPoint(t)));
        }
        return null;
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
        return q0.equals(plane.q0) && normal.equals(plane.normal);
    }

    /**
     *toString
     * @return Threading the values
     */
    @Override
    public String toString() {
        return "Plane:\n"+"p0: " + q0.toString() +"\nnormal: " + normal.toString();
    }
}

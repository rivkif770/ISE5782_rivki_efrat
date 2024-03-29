package geometries;

import primitives.*;
import static primitives.Util.*;
import java.util.List;
/**
 * this class represent a Plane with point and vector normal that standing on the point
 *
 * @author efrat and rivki
 */
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
     * Constructor of Plane from 3 points on its surface <br/>
     * the points are ordered from right to left
     * we calculate the normal on the constructor to avoid repeated request of the normal
     * @param p1 P1
     * @param p2 P2
     * @param p3 P3
     * @throws IllegalArgumentException if UxV = (0,0,0) => all 3 point on the same line
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
    //region getters
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
    //endregion
    /**
     * find intersections point with the plane
     * @param ray ray that cross the plane
     * @return list of intersection points that were found => p0 + tv
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelperHelper(Ray ray, double maxDistance) {
        if(isZero(ray.getDir().dotProduct(normal)))
        {
            return null;
        }
        double t;
        try {
            t = (double)normal.dotProduct(q0.subtract(ray.getP0()))/normal.dotProduct(ray.getDir());
        }
        catch (IllegalArgumentException e) {
            return null;
        }
        if (t<=0) return null;
        //A test that verifies that the point is indeed a point of intersection and is within the desired range
        if (alignZero(t - maxDistance) > 0)
            return null;
        return List.of(new GeoPoint(this, ray.getPoint(t)));
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

package geometries;

import primitives.*;

import java.util.List;
import java.util.Objects;

import static primitives.Util.*;

/**
 * this class represent a sphere by center point and radius
 * @author  efrat and rivki
 */
public class Sphere extends Geometry{
    private Point center;
    private double radius;

    /**
     * constructor
     * @param center - center of the sphere
     * @param radius - radius of the sphere
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        if(radius == 0)
            throw new IllegalArgumentException("Invalid radius");
        this.radius = radius;
    }
    //region getters
    /**
     * get center
     * @return center point of sphere
     */
    public Point getCenter() {
        return center;
    }

    /**
     * get radius
     * @return radius of sphere
     */
    public double getRadius() {
        return radius;
    }

    /**
     * the normal of sphere:
     * n = normalize(p - centerPoint)
     * @param point the point on the sphere we want the normal from
     * @return normal vector
     */
    @Override
    public Vector getNormal(Point point) {
        if(point.equals(center)) throw new IllegalArgumentException("point cannot be equals to the center of the sphere");

        Vector o_p = point.subtract(center);
        return o_p.normalize();
    }
    //endregion
    /**
     * Comparison between spheres
     * @param o
     * @return Boolean value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sphere sphere = (Sphere) o;
        return Double.compare(sphere.radius, radius) == 0 && center.equals(sphere.center);
    }

    /**
     * find intersections point with the sphere
     * let O be the center of the sphere, let r be the radius <br/>
     * u = O − p0 <br/>
     * tm = v * u <br/>
     * d = sqrt ( |u|^2 - tm^2 ) <br/>
     * th = sqrt ( r^2 - d^2 ) <br/>
     * if (d >= r) there are no intersections <br/>
     * t1,2 = tm +- th, p1,2 = p0 + t1,2*v <br/>
     * t1,2*v => take only if t > 0 <br/>
     * @param ray ray that cross the sphere
     * @return list of intersection points that were found
     * @throws IllegalArgumentException if the starting point of the ray equals to the center of the sphere
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelperHelper(Ray ray, double maxDistance) {
        Point rayQ0 = ray.getP0();
        Vector rayDir = ray.getDir();
        if(rayQ0.equals(center))
            return List.of(new GeoPoint(this, ray.getPoint(radius)));
        Vector q0ToCenter = center.subtract(rayQ0);
        double tm = alignZero(rayDir.dotProduct(q0ToCenter)); ;
        double d = alignZero(Math.sqrt(q0ToCenter.lengthSquared()-tm*tm));
        if(d>=radius)
            return null;
        double th = alignZero(Math.sqrt(radius*radius - d*d));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        //A list of conditions that verify that the points are indeed intersection points and are within the desired range
        if((t1 <= 0 )&& ( t2 <= 0 ))
            return null;
        if((t1 <= 0)&&( t2 > 0 && (alignZero(t2 - maxDistance) <= 0)))
            return List.of(new GeoPoint(this,ray.getPoint(t2)));
        if((t1 > 0 && (alignZero(t1 - maxDistance) <= 0))&&( t2 <= 0 ))
            return List.of(new GeoPoint(this,ray.getPoint(t1)));
        if((t1 > 0 && (alignZero(t1 - maxDistance) <= 0))&&( t2 > 0 && (alignZero(t2 - maxDistance) <= 0)))
            return List.of(new GeoPoint(this,ray.getPoint(t1)), new GeoPoint(this,ray.getPoint(t2)));
        return null;
    }

    /**
     * toString
     * @return string
     */
    @Override
    public String toString() {
        return "Sphere: " +
                "\ncenter: " + center +
                "\nradius: " + radius;
    }
}

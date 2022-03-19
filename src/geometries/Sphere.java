package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.Util.*;

import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;

public class Sphere implements Geometry{
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
     * Returns normal to point
     * @param point
     * @return normal
     */
    @Override
    public Vector getNormal(Point point) {
        if(point.equals(center)) throw new IllegalArgumentException("point cannot be equals to the center of the sphere");

        Vector o_p = point.subtract(center);
        return o_p.normalize();
    }

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
     * toString
     * @return string
     */
    @Override
    public String toString() {
        return "Sphere: " +
                "\ncenter: " + center +
                "\nradius: " + radius;
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
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        if(p0.equals(center)){
            throw new IllegalArgumentException("ray p0 cannot be equals to the center of the sphere");
        }

        Vector u = center.subtract(p0);
        double tm = u.dotProduct(v);
        double d = alignZero(Math.sqrt(u.lengthSquared() - (tm * tm) ));

        if(d >= radius){
            return null; // there is no intersections points
        }

        double th = alignZero(Math.sqrt( (radius * radius) - (d * d) ));

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if(t1 > 0 && t2 > 0){
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);

            return List.of(new Point(p1.getXyz()), new Point( p2.getXyz()));
        }

        if(t1 > 0){
            return List.of(new Point(ray.getPoint(t1).getXyz()));
        }

        if(t2 > 0){
            return List.of(new Point(ray.getPoint(t2).getXyz()));
        }

        return null; // 0 points
    }
}

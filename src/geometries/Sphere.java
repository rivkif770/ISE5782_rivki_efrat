package geometries;

import primitives.Point;
import primitives.Vector;

import java.util.Objects;

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
}

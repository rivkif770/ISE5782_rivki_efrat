package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere implements Geometry{
    private Point center;
    private double radius;

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
     * toString
     * @return
     */
    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}

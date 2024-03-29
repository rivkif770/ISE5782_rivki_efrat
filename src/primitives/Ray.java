package primitives;
import geometries.Intersectable.GeoPoint;

import java.util.List;
import java.util.Objects;

/**
 * this class represent a ray by starting point and direction
 * @author rivki and efrat
 */
public class Ray {
    private Point p0;
    private Vector dir;
    /**
     * constants for size moving first rays for shading rays
     */
    private static final double DELTA = 0.1d;

    /**
     * constructor ray
     * @param p0-Vector's starting point
     * @param dir-The direction of the vector
     * Normalize the vector in a state that is not normalized
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();

    }
    /**
     * constructor for creating a ray with small movement of the starting point
     * @param point the starting point that on some geometry
     * @param dir the direction of the ray
     * @param n normal to the point on some geometry
     */
    public Ray(Point point, Vector dir, Vector n) {
        Vector delta = n.scale(n.dotProduct(dir) > 0d ? DELTA : - DELTA);
        p0 = point.add(delta);
        this.dir = dir;
    }

    /**
     * geter of point
     * @return p0-Vector's starting point
     */
    public Point getP0() {
        return p0;
    }

    /**
     * geter of vector
     * @return dir-The direction of the vector
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * p = p0 + tv
     * v is the direction of the ray, p0 is the stating point of the ray
     * @param t scalar
     * @return point on the ray
     */
    public Point getPoint(double t){
        return p0.add(dir.scale(t));
    }

    /**
     * find the closest point to the starting point of the ray in list of GeoPoints
     * @param geoPoints list of GeoPoints
     * @return the closest GeoPoint
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPoints){
        if(geoPoints == null){
            return null;
        }

        GeoPoint closesGeoPoint = null;
        double minDistance = Double.MAX_VALUE;

        for(var geoPoint : geoPoints){
            double temp = geoPoint.point.distance(p0);
            if(minDistance > temp){
                closesGeoPoint = geoPoint;
                minDistance = temp;
            }
        }

        return closesGeoPoint;
    }

    /**
     * equals-Compares two rays
     * @param o-The object for comparison
     * @return Boolean value Whether the objects are equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return getP0().equals(ray.getP0()) && getDir().equals(ray.getDir());
    }

    /**
     *toString
     * @return Threading the values
     */
    @Override
    public String toString() {
        return p0.toString()+ dir.toString();
    }
    /**
     * find the closest point to the starting point of the ray in list of Points
     * @param points list of Points
     * @return the closest Point
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }
}

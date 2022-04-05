package primitives;
import geometries.Intersectable.GeoPoint;

import java.util.List;
import java.util.Objects;

public class Ray {
    private Point p0;
    private Vector dir;

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
    public Point getPoint(double t){
        return p0.add(dir.scale(t));
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
    public Point findClosestPoint(List<Point> points){
        if(points == null){
            return null;
        }

        Point closesPoint = null;
        double minDistance = Double.MAX_VALUE;

        for(var point : points){
            double temp = point.distance(p0);
            if(minDistance > temp){
                closesPoint = point;
                minDistance = temp;
            }
        }
        return closesPoint;
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


}

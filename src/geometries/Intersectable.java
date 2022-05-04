package geometries;
import primitives.*;

import java.util.*;
/**
 * interface to get intersection points of ray with some geometry
 * @author efrat and rivki
 */
public abstract class Intersectable {
    /**
     * A class that contains a point and the geometry that contains it
     */
    public static class GeoPoint {
        /**
         * the geometry that we find the color of a certain point
         */
        public Geometry geometry;
        /**
         * the point on the geometry that we get the color from
         */
        public Point point;

        /**
         * constructor for the GeoPoint class
         * @param geometry the geometry
         * @param point the point that interact the geometry
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        /**
         * equals
         * @param o the Object
         * @return boolean value
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint)) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }
        /**
         * toString
         * @return string
         */
        @Override
        public String toString() {
            return "GeoPoint:\n" +
                    "geometry: " + geometry +
                    "\npoint: " + point ;
        }
    }
    /**
     * find intersections of ray with geometry shape
     * @param ray ray that cross the geometry
     * @return list of intersection points that were found
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * find intersections of ray with geometry shape
     * @param ray ray that cross the geometry
     * @return list of intersection points that were found
     */


    /**
     *
     * @param ray
     * @return
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelperHelper(ray, maxDistance);
    }
    protected abstract List<GeoPoint> findGeoIntersectionsHelperHelper(Ray ray, double maxDistance);


}

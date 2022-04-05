package geometries;
import primitives.*;

import java.util.*;

public abstract class Intersectable {
    public abstract List<Point> findIntersections(Ray ray);

    /**
     *
     * @param rar
     * @return
     */
    public List<GeoPoint> findGeoIntersections(Ray rar){
        return findGeoIntersectionsHelper(rar);
    }

    /**
     *
     * @param ray
     * @return
     */
    private List<GeoPoint> findGeoIntersectionsHelper(Ray ray){

    }
    /**
     * A class that contains a point and the geometry that contains it
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         * constructor of GeoPoint
         * @param geometry-The geometry in which the dot is contained
         * @param point-point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        /**
         *
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint)) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint:\n" +
                    "geometry: " + geometry +
                    "\npoint: " + point ;
        }
    }

}

package geometries;

import static primitives.Util.*;
import primitives.*;
import java.util.*;
import primitives.Vector;

/**
 * this class represent a Triangle by 3 points
 * @author efrat and rivki
 */
public class Triangle extends Polygon{

    /**
     * constructor
     * @param p1 Vertex 1
     * @param p2 Vertex 2
     * @param p3 Vertex 3
     */
    public Triangle(Point p1,Point p2,Point p3) {
        super(p1,p2,p3);
    }
    /**
     * Returns the list of the 3 triangle points
     * @return vertices - the list of the triangle's points
     */
    public List<Point> getVertices() {
        return this.vertices;
    }

    /**
     * Returns normal to point
     * @param point
     * @return normal
     */
    public Vector getNormal(Point point){return super.getNormal(point);}

    /**
     * equals
     * @param o
     * @return boolean value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return super.equals(o);
    }

    /**
     * find intersections point with the triangle
     * v1 = p1 - p0 <br/>
     * v2 = p2 - p0 <br/>
     * v3 = p3 - p0 <br/>
     * n1 = normalize(v1xv2) <br/>
     * n2 = normalize(v2xv3) <br/>
     * n3 = normalize(v3xv1) <br/>
     * let v be the direction of the rey
     * if v*ni (i between 1 to 3) is have the sing (+/-)
     * there is intersection points with the triangle
     * @param ray ray that cross the triangle
     * @return list of intersection points that were found
     */
    @Override
    public  List<GeoPoint> findGeoIntersectionsHelperHelper(Ray ray, double maxDistance){
        List<GeoPoint> resultOfPlane = plane.findGeoIntersections(ray, maxDistance);
        if (resultOfPlane == null)
            return null;
        List<GeoPoint> result = new LinkedList<GeoPoint>();

        Vector v1 = getVertices().get(0).subtract(ray.getP0());
        Vector v2 = getVertices().get(1).subtract(ray.getP0());
        Vector v3 = getVertices().get(2).subtract(ray.getP0());
        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        Vector v = ray.getDir();
        double r1 = alignZero(v.dotProduct(n1));
        double r2 = alignZero(v.dotProduct(n2));
        double r3 = alignZero(v.dotProduct(n3));

        if ((r1 > 0 && r2 > 0 && r3 > 0) || (r1 < 0 && r2 < 0 && r3 < 0)) {
            for (GeoPoint point: resultOfPlane) {
                result.add(new GeoPoint(this, point.point));
            }
            return result;
        }

        return null;
    }

    /**
     * toString
     * @return string
     */
    @Override
    public String toString() {
        return "Triangle: \n" +
                super.toString();
    }
}

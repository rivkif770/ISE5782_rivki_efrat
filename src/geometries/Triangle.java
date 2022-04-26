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
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        var result = plane.findGeoIntersectionsHelper(ray);

        // if there is no intersections with the plane is a fortiori (kal&homer)
        // that there is no intersections with the triangle
        if(result == null){
            return null;
        }

        Vector v1 = this.vertices.get(0).subtract(p0),
                v2 = this.vertices.get(1).subtract(p0),
                v3 = this.vertices.get(2).subtract(p0);

        Vector n1 = v1.crossProduct(v2).normalize(),
                n2 = v2.crossProduct(v3).normalize(),
                n3 = v3.crossProduct(v1).normalize();

        double x1 = alignZero(v.dotProduct(n1)),
                x2 = alignZero(v.dotProduct(n2)),
                x3 = alignZero(v.dotProduct(n3));

        boolean allNegative = x1 < 0 && x2 < 0 && x3 < 0;
        boolean allPositive = x1 > 0 && x2 > 0 && x3 > 0;

        if(allNegative || allPositive){
            return List.of(new GeoPoint(this, result.get(0).point)); // return the intersections with the plane that the triangle is on
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

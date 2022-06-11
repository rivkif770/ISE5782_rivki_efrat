
package geometries;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import primitives.*;

/**
 *Spgere
 *
 * @author Idan and Eliyahu
 */
public class Elepsoaide extends Geometry{
    Point center;
    double radius2;
    double radius3 ;
    double radius1 ;

    /**
     * constructor for Elepsoaide
     * @param center center of Elepsoaide
     * @param radius1 Length
     * @param radius2 Width
     * @param radius3 Height
     */
    public Elepsoaide(Point center, double radius1 , double radius2 ,double radius3 ) {//simple constructor
        this.center = center ;
        this.radius1 = radius1 ;
        this.radius2 = radius2 ;
        this.radius3 = radius3 ;
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }


//    @Override
//    public List<GeoPoint> findIntsersections(Ray rayC, double maxDistance){
//        Point centerZero = new Point(0,0,0);
//        rayC = new Ray(rayC.getP0().subtract(center), rayC.getDir());
//        double h1 = rayC.getDir().getX() , h2 = rayC.getDir().getY() , h3  = rayC.getDir().getZ()
//                ,h12 = h1 * h1 , h22 = h2 * h2 , h32 = h3 * h3 ,
//                q1 = rayC.getP0().getX() , q2 = rayC.getP0().getY() , q3 =rayC.getP0().getZ(),
//                q12 = q1 * q1 , q22 = q2 *q2 ,q32 = q3 * q3 ;
//        double r12 = radius1 * radius1 , r22 = radius2 * radius2  , r32 = radius3 * radius3 ;
//        double a = h12 * r22 * r32 + h22 *r12 * r32 + h32 *r12 * r22 ,
//                b = 2 * (h1*q1*r22*r32 + h2*q2 * r12*r32 + h3*q3*r12*r22) ,
//                c =  q12*r22*r32 + q22 * r12*r32  + q32*r12*r22 - r12 * r22 *r32 ;
//        double sqrt = b*b - 4 * a*c ;
//        if(sqrt < 0 ){
//            return null ;
//        }
//        sqrt = Math.sqrt(sqrt);
//        Point centerOps = new Point(center.getX() *-1 , center.getY() * -1 , center.getZ() * -1);
//        if(sqrt == 0 ){
//            double t = sqrt / (2 * a);
//            return List.of(new Point(h1*t + q1 , h2 * t + q2 , h3 * t + q3).subtract(centerOps));
//        }
//        double t1 = (-b - sqrt)/(2*a) ;
//        double t2 = (-b - sqrt)/(2*a) ;
//        return List.of(
//                new Point(h1*t1 + q1 , h2 * t1 + q2 , h3 * t1 + q3).subtract(centerOps),
//                new Point(h1*t2 + q1 , h2 * t2 + q2 , h3 * t2 + q3).subtract(centerOps)
//        );
//    }


    /**
     * find intersections point with the Elepsoaide
     * @param ray ray that cross the Elepsoaide
     * @param maxDistance Maximum distance
     * @return list of intersection points that were found
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelperHelper(Ray ray, double maxDistance) {
        Point centerZero = new Point(0,0,0);
        ray = new Ray(ray.getP0().subtract(center), ray.getDir());
        double h1 = ray.getDir().getX() , h2 = ray.getDir().getY() , h3  = ray.getDir().getZ()
                ,h12 = h1 * h1 , h22 = h2 * h2 , h32 = h3 * h3 ,
                q1 = ray.getP0().getX() , q2 = ray.getP0().getY() , q3 =ray.getP0().getZ(),
                q12 = q1 * q1 , q22 = q2 *q2 ,q32 = q3 * q3 ;
        double r12 = radius1 * radius1 , r22 = radius2 * radius2  , r32 = radius3 * radius3 ;
        double a = h12 * r22 * r32 + h22 *r12 * r32 + h32 *r12 * r22 ,
                b = 2 * (h1*q1*r22*r32 + h2*q2 * r12*r32 + h3*q3*r12*r22) ,
                c =  q12*r22*r32 + q22 * r12*r32  + q32*r12*r22 - r12 * r22 *r32 ;
        double sqrt = b*b - 4 * a*c ;
        if(sqrt < 0 ){
            return null ;
        }
        sqrt = Math.sqrt(sqrt);
        Point centerOps = new Point(center.getX() *-1 , center.getY() * -1 , center.getZ() * -1);
        if(sqrt == 0 ){
            double t = sqrt / (2 * a);
            return List.of(new Intersectable.GeoPoint(this,new Point(h1*t + q1 , h2 * t + q2 , h3 * t + q3).subtract(centerOps)));
        }
        double t1 = (-b - sqrt)/(2*a) ;
        double t2 = (-b - sqrt)/(2*a) ;
        return List.of(
                new Intersectable.GeoPoint(this,new Point(h1*t1 + q1 , h2 * t1 + q2 , h3 * t1 + q3).subtract(centerOps)) ,
                new Intersectable.GeoPoint(this,new Point(h1*t2 + q1 , h2 * t2 + q2 , h3 * t2 + q3).subtract(centerOps))
        );
    }


}
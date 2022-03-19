package primitives;
import java.math.*;
import java.util.Objects;
import primitives.Double3;
public class Point {
    protected Double3 xyz;

    /**
     * constructor
     * @param x value of x
     * @param y value of y
     * @param z value of z
     */
    public Point(double x, double y, double z) {
        xyz=new Double3(x,y,z);
    }
    /**
     *constructor
     * @param double3 value of point
     */
    public Point(Double3 double3){
        xyz=double3;
    }
    /**
     *getter of point
     * @return Double3
     */
    public Double3 getXyz() {
        return xyz;
    }
    /**
     * getX of point
     * @return
     */
    public double getX() {return xyz.d1; }
    /**
     * getY of point
     * @return
     */
    public double getY() {return xyz.d2; }
    /**
     * getZ of point
     * @return
     */
    public double getZ() {return xyz.d3; }
    /**
     *Connecting vector and dot
     * @param vector to adding
     * @return point
     */
    public Point add(Vector vector) {
        return new Point(this.xyz.add(vector.xyz));
    }
    /**
     *Subtraction vector and dot
     * @param point to subtract
     * @return Vector
     */
    public Vector subtract(Point point) {
         return new Vector(this.xyz.subtract(point.xyz));
    }
    /**
     *Distance squared
     * @param point
     * @return Distance squared
     */
    public double distanceSquared(Point point) {
        Double3 result =this.xyz.subtract(point.xyz);
        return (result.d1*result.d1)+(result.d2*result.d2)+(result.d3*result.d3);
    }
    /**
     *Distance
     * @param point
     * @return Distance
     */
    public double distance(Point point) {
        return Math.sqrt(this.distanceSquared(point));
    }
    /**
     *Compares two points
     * @param o
     * @return Boolean value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return xyz.equals(point.xyz);
    }
    /**
     *toString
     * @return
     */
    @Override
    public String toString() {
        return xyz.toString();
    }

}

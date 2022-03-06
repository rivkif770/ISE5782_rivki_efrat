package primitives;
import java.math.*;
import java.util.Objects;
import primitives.Double3;
public class Point {
    protected Double3 xyz;

    public Point(double x, double y, double z) {
        xyz=new Double3(x,y,z);
    }
    public Point(Double3 double3){
        xyz=double3;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return xyz.equals(point.xyz);
    }

    @Override
    public String toString() {
        return xyz.toString();
    }

    public Vector subtract(Point point) {
         return new Vector(this.xyz.subtract(point.xyz));
    }
    public Point add(Vector vector) {
        return new Point(this.xyz.add(vector.xyz));
    }
    public double distanceSquared(Point point) {
        Double3 result =this.xyz.subtract(point.xyz);
        return (result.d1*result.d1)+(result.d2*result.d2)+(result.d3*result.d3);
    }
    public double distance(Point point) {
        return Math.sqrt(this.distanceSquared(point));
    }

    public Double3 getXyz() {
        return xyz;
    }
}

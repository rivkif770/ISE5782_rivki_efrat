package geometries;

import primitives.Point;
import primitives.Vector;

import java.util.Objects;

public class Plane implements Geometry {
    public Point p0;
    public Vector normal;

    public Plane(Point p0, Vector normal) {
        this.p0 = p0;
        if(normal.length()==1)
            this.normal = normal;
        else
            normal.normalize();
    }
    public Plane(Point x,Point y,Point z){
        //normal = x.subtract(y).crossProduct(y.subtract(z));
        normal = null;
        p0 = x;
    }
    public Point getP0() {
        return p0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane)) return false;
        Plane plane = (Plane) o;
        return p0.equals(plane.p0) && normal.equals(plane.normal);
    }


    @Override
    public String toString() {
        return "Plane:\n"+"p0: " +p0.toString() +"\nnormal: " + normal.toString();
    }
    public Vector getNormal() {
        return normal;
    }
    @Override
    public Vector getNormal(Point point) {
        return normal;
    }
}

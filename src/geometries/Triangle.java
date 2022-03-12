package geometries;

import primitives.Point;
import primitives.Vector;

import java.util.Objects;

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
     * toString
     * @return string
     */
    @Override
    public String toString() {
        return "Triangle: \n" +
                super.toString();
    }
}

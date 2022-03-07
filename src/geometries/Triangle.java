package geometries;

import primitives.Point;

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
}

package primitives;

import java.util.Objects;

public class Vector extends Point {
    /**
     * constructor
     * @param x value of x
     * @param y value of y
     * @param z value of z
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if(Double3.ZERO.equals(new Double3(x,y,z)))
            throw new IllegalArgumentException("vector 0");
    }
    /**
     * constructor
     * @param double3 of point
     */
    public Vector(Double3 double3){
        super(double3);
        if(Double3.ZERO.equals(double3))
            throw new IllegalArgumentException("vector 0");
    }
    /**
     * add
     * @param vector to adding
     * @return vector
     */
    public Vector add(Vector vector) {
        return new Vector(xyz.add(vector.xyz));
    }
    /**
     * subtract
     * @param vector to subtract
     * @return Vector
     */
    public Vector subtract(Vector vector) {
        return this.subtract(new Point(vector.xyz));
    }
    /**
     * Hemisphere multiplication
     * @param num
     * @return vector
     */
    public Vector scale (double num){
        if (num==0)
            throw new IllegalArgumentException("vector 0");
        return new Vector(xyz.d1*num, xyz.d2*num, xyz.d3*num);
    }
    /**
     *Scalar product
     * @param vector
     * @return Vector
     */
    public double dotProduct(Vector vector){
        return (vector.xyz.d1 * xyz.d1)+(vector.xyz.d2 * xyz.d2)+(vector.xyz.d3 * xyz.d3);
    }
    /**
     *cross product
     * @param vector
     * @return vector
     */
    public Vector crossProduct(Vector vector){
        return new Vector(xyz.d2*vector.xyz.d3-xyz.d3*vector.xyz.d2, xyz.d3*vector.xyz.d1- xyz.d1*vector.xyz.d3, xyz.d1*vector.xyz.d2- xyz.d2*vector.xyz.d1);
    }
    /**
     *Distance squared
     * @return Distance squared
     */
    public double lengthSquared(){
        return (xyz.d1 * xyz.d1)+(xyz.d2 * xyz.d2)+(xyz.d3 * xyz.d3);
    }
    /**
     * Distance
     * @return Distance
     */
    public double length(){
        return Math.sqrt(this.lengthSquared());
    }
    /**
     *Vector normalization
     * @return
     */
    public Vector normalize(){
        return new Vector(xyz.reduce(length()));
    }
    /**
     *Compares two vectors
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector)) return false;
        if (!super.equals(o)) return false;
        Vector vector = (Vector) o;
        return super.equals(new Point(vector.xyz));
    }
    /**
     *toString
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    }

}

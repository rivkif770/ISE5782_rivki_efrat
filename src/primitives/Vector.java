package primitives;

import java.util.Objects;

/**
 * this class represent a Vector of 3D
 * @author rivki and efrat
 */
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
     * @param double3 value of point
     */
    public Vector(Double3 double3){
        super(double3);
        if(Double3.ZERO.equals(double3))
            throw new IllegalArgumentException("vector 0");
    }
    /**
     * add tow vectors
     * @param vector the other vector to add
     * @return new Vector from (x1 + x2, y1 + y2, z1 + z2)
     */
    public Vector add(Vector vector) {
        return new Vector(xyz.add(vector.xyz));
    }
    /**
     * subtract two vectors
     * @param vector the other vector to subtract with
     * @return new Vector from (x1 - x2, y1 - y2, z1 - z2)
     */
    public Vector subtract(Vector vector) {
        return this.subtract(new Point(vector.xyz));
    }
    /**
     * multiply this vector with a scalar
     * @param num the scalar
     * @return new Vector with the value of (scalar * x, scalar * y, scalar * z)
     * @throws IllegalArgumentException if scalar = 0
     */
    public Vector scale (double num){
        if (num==0)
            throw new IllegalArgumentException("vector 0");
        return new Vector(xyz.d1*num, xyz.d2*num, xyz.d3*num);
    }
    /**
     * dot product of tow vectors (x1, y1, z1), (x2, y2, z2)
     * @param vector (x2, y2, z2)
     * @return x1*x2 + y1*y2 + z1*z2
     */
    public double dotProduct(Vector vector){
        return (vector.xyz.d1 * xyz.d1)+(vector.xyz.d2 * xyz.d2)+(vector.xyz.d3 * xyz.d3);
    }
    /**
     * cross product of two vectors (x1, y1, z1), (x2, y2, z2)
     * @param vector (x2, y2, z2)
     * @return new Vector from (x1, y1, z1)X(x2, y2, z2) =
     * = (y1*z2 -z1*y2, z1*x2 - x1*z2, x1*y2 - y1*x2)
     */
    public Vector crossProduct(Vector vector){
        return new Vector(xyz.d2*vector.xyz.d3-xyz.d3*vector.xyz.d2, xyz.d3*vector.xyz.d1- xyz.d1*vector.xyz.d3, xyz.d1*vector.xyz.d2- xyz.d2*vector.xyz.d1);
    }
    /**
     * the squared length of the vector (x, y, z)
     * @return x^2 + y^2 + z^2
     */
    public double lengthSquared(){
        return (xyz.d1 * xyz.d1)+(xyz.d2 * xyz.d2)+(xyz.d3 * xyz.d3);
    }
    /**
     * the length of the vector (x, y, z)
     * @return sqrt(x^2 + y^2 + z^2)
     */
    public double length(){
        return Math.sqrt(this.lengthSquared());
    }
    /**
     * the normal of this vector of (x, y, z)
     * @return (x, y, z) / |(x, y, z)| = (x, y, z) / sqrt(x^2 + y^2 + z^2)
     */
    public Vector normalize(){
        return new Vector(xyz.reduce(length()));
    }

    /**
     * Rotates the vector around the x-axis
     *
     * @param alpha the amount to rotate in degrees
     * @return the current vector
     */
    public Vector rotateX(double alpha) {
        double radianAlpha = alpha * Math.PI / 180;

        double x = getX();
        double y = getY() * Math.cos(radianAlpha) - getZ() * Math.sin(radianAlpha);
        double z = getY() * Math.sin(radianAlpha) + getZ() * Math.cos(radianAlpha);

        return new Vector(x, y, z);
    }


    /**
     * Rotates the vector around the y axis
     *
     * @param alpha the amount to rotate in degrees
     * @return the current vector
     */
    public Vector rotateY(double alpha) {
        double radianAlpha = alpha * Math.PI / 180;

        double x = getX() * Math.cos(radianAlpha) + getZ() * Math.sin(radianAlpha);
        double y = getY();
        double z = -getX() * Math.sin(radianAlpha) + getZ() * Math.cos(radianAlpha);

        return new Vector(x, y, z);
    }


    /**
     * Rotates the vector around the z axis
     *
     * @param alpha the amount to rotate in degrees
     * @return the current vector
     */
    public Vector rotateZ(double alpha) {
        double radianAlpha = alpha * Math.PI / 180;

        double x = getX() * Math.cos(radianAlpha) - getY() * Math.sin(radianAlpha);
        double y = getX() * Math.sin(radianAlpha) + getY() * Math.cos(radianAlpha);
        double z = getZ();

        return new Vector(x, y, z);
    }
    /**
     *Compares two Vectors
     * @param o
     * @return Boolean value
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
     *string of vector
     * @return string
     */
    @Override
    public String toString() {
        return super.toString();
    }

}

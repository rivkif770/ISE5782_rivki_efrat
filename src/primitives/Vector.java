package primitives;

import java.util.Objects;

public class Vector extends Point {
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if(Double3.ZERO.equals(new Double3(x,y,z)))
            throw new IllegalArgumentException("vector 0");
    }
    public Vector(Double3 double3){
        super(double3);
        if(Double3.ZERO.equals(double3))
            throw new IllegalArgumentException("vector 0");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector)) return false;
        if (!super.equals(o)) return false;
        Vector vector = (Vector) o;
        return super.equals(new Point(vector.xyz));
    }

    public Vector subtract(Vector vector) {
        return this.subtract(new Point(vector.xyz));
    }

    public Vector add(Vector vector) {
        return new Vector(this.add(vector).xyz);
    }
    public Vector scale (double num){
        if (num==0)
            throw new IllegalArgumentException("vector 0");
        return new Vector(xyz.d1*num, xyz.d2*num, xyz.d3*num);
    }
    public double dotProduct(Vector vector){
        return (vector.xyz.d1 * xyz.d1)+(vector.xyz.d2 * xyz.d2)+(vector.xyz.d3 * xyz.d3);
    }
    public Vector crossProduct(Vector vector){
       return new Vector(xyz.d2*vector.xyz.d3-xyz.d3*vector.xyz.d2, xyz.d3*vector.xyz.d1- xyz.d1*vector.xyz.d3, xyz.d1*vector.xyz.d2- xyz.d2*vector.xyz.d1);
    }
    public double lengthSquared(){
        return (xyz.d1 * xyz.d1)+(xyz.d2 * xyz.d2)+(xyz.d3 * xyz.d3);
    }
    public double length(){
        return Math.sqrt(this.lengthSquared());
    }
    public Vector normalize(){
        double length=this.length();
        return new Vector(xyz.d1/length,xyz.d2/length,xyz.d3/length);
    }
    @Override
    public String toString() {
        return super.toString();
    }

}

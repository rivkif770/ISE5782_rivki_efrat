package primitives;

import java.util.Objects;

public class Ray {
    private Point p0;
    private Vector dir;

    public Ray(Point p0, Vector dir) {
        if(dir.length()!=1)
            dir.normalize();
        this.p0 = p0;
        this.dir = dir;

    }

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return getP0().equals(ray.getP0()) && getDir().equals(ray.getDir());
    }

    @Override
    public String toString() {
        return p0.toString()+ dir.toString();
    }
}

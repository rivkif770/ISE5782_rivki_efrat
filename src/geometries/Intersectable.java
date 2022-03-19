package geometries;
import primitives.*;

import java.util.*;

public interface Intersectable {
    public List<Point> findIntersections(Ray ray);
}

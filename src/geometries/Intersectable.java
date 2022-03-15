package geometries;
import primitives.*;

import java.util.*;

public interface Intersectable {
    public List<Point> findIntsersections(Ray ray);
}

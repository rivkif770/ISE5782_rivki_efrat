package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{
    private List<Intersectable> _intersectables;

    /**
     * Default constructor for Geometries
     */
    public Geometries() {
        // use LinkedList because the use of this list is only for iterate
        // from the start of the list to the end, and adding new items to her
        _intersectables = new LinkedList();
    }

    /**
     * Constructor for Geometries
     * @param intersectables one or more interfaces to add to the geometries list
     */
    public Geometries(Intersectable... intersectables) {
        this();
        add(intersectables);
    }

    /**
     * Add interfaces to the list of the geometries
     * @param intersectables one or more interfaces to add to the geometries list
     */
    public void add(Intersectable... intersectables){
        for(var item : intersectables){
            _intersectables.add(item);
        }
    }

    /**
     * find intersections of ray with geometry shape
     *
     * @param ray ray that cross the geometry
     * @return list of intersection points that were found
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = null;
        for(var item : _intersectables){
            List<Point>  itemPoints = item.findIntersections(ray);
            if(itemPoints != null){
                if(result == null){
                    result = new LinkedList();
                }
                result.addAll(itemPoints);
            }
        }
        return result;
    }
}

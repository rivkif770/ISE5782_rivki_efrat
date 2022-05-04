package geometries;

import java.util.List;
import java.util.Objects;

import primitives.*;
import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * 
 * @author Dan
 */
public class Polygon extends Geometry {
	/**
	 * List of polygon's vertices
	 */
	protected List<Point> vertices;
	/**
	 * Associated plane in which the polygon lays
	 */
	protected Plane plane;
	private int size;

	/**
	 * Polygon constructor based on vertices list. The list must be ordered by edge
	 * path. The polygon must be convex.
	 * 
	 * @param vertices list of vertices according to their order by edge path
	 * @throws IllegalArgumentException in any case of illegal combination of
	 *                                  vertices:
	 *                                  <ul>
	 *                                  <li>Less than 3 vertices</li>
	 *                                  <li>Consequent vertices are in the same
	 *                                  point
	 *                                  <li>The vertices are not in the same
	 *                                  plane</li>
	 *                                  <li>The order of vertices is not according
	 *                                  to edge path</li>
	 *                                  <li>Three consequent vertices lay in the
	 *                                  same line (180&#176; angle between two
	 *                                  consequent edges)
	 *                                  <li>The polygon is concave (not convex)</li>
	 *                                  </ul>
	 */
	public Polygon(Point... vertices) {
		if (vertices.length < 3)
			throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
		this.vertices = List.of(vertices);
		// Generate the plane according to the first three vertices and associate the
		// polygon with this plane.
		// The plane holds the invariant normal (orthogonal unit) vector to the polygon
		plane = new Plane(vertices[0], vertices[1], vertices[2]);
		if (vertices.length == 3)
			return; // no need for more tests for a Triangle

		Vector n = plane.getNormal();

		// Subtracting any subsequent points will throw an IllegalArgumentException
		// because of Zero Vector if they are in the same point
		Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
		Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

		// Cross Product of any subsequent edges will throw an IllegalArgumentException
		// because of Zero Vector if they connect three vertices that lay in the same
		// line.
		// Generate the direction of the polygon according to the angle between last and
		// first edge being less than 180 deg. It is hold by the sign of its dot product
		// with
		// the normal. If all the rest consequent edges will generate the same sign -
		// the
		// polygon is convex ("kamur" in Hebrew).
		boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
		for (var i = 1; i < vertices.length; ++i) {
			// Test that the point is in the same plane as calculated originally
			if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
				throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
			// Test the consequent edges have
			edge1 = edge2;
			edge2 = vertices[i].subtract(vertices[i - 1]);
			if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
				throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
		}
		size = vertices.length;
	}

	/**
	 * A function that returns a normal point
	 * @param point
	 * @return
	 */
	@Override
	public Vector getNormal(Point point) {
		return plane.getNormal();
	}

	/**
	 * equals
	 * @param o
	 * @return
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Polygon polygon = (Polygon) o;
		return size == polygon.size && vertices.equals(polygon.vertices) && plane.equals(polygon.plane);
	}

	/**
	 * find intersections of ray with geometry shape
	 *
	 * @param ray ray that cross the geometry
	 * @return list of intersection points that were found
	 */
	@Override
	public  List<GeoPoint> findGeoIntersectionsHelperHelper(Ray ray, double maxDistance) {
		List<GeoPoint> result = plane.findGeoIntersections(ray, maxDistance);

		if (result == null) {
			return result;
		}

		result=List.of(new GeoPoint(this,result.get(0).point));

		Point P0 = ray.getP0();//the start ray point
		Vector v = ray.getDir();

		Point P1 = vertices.get(1);
		Point P2 = vertices.get(0);

		Vector v1 = P1.subtract(P0); //vector from the ray start point to the polygon vertices
		Vector v2 = P2.subtract(P0);//vector from the ray start point to the polygon vertices

		double sign = alignZero(v.dotProduct(v1.crossProduct(v2)));

		if (isZero(sign)) {//out of the polygon
			return null;
		}

		boolean positive = sign > 0;

		//iterate through all vertices of the polygon
		for (int i = vertices.size() - 1; i > 0; --i) { //foreach vertices
			v1 = v2;
			v2 = vertices.get(i).subtract(P0);//vector from the ray start point to the polygon vertices

			sign = alignZero(v.dotProduct(v1.crossProduct(v2)));
			if (isZero(sign)) {//out of the polygon
				return null;
			}

			if (positive != (sign > 0)) {//out of the polygon
				return null;
			}
		}

		return result;
	}
	/**
	 * toString
	 * @return
	 */
	@Override
	public String toString() {
		return "Polygon: " +
				"\nvertices: " + vertices +
				"\nplane: " + plane +
				"\nsize: " + size;
	}


}

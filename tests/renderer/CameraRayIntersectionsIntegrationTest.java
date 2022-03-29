package renderer;

import org.junit.jupiter.api.Test;
import primitives.*;

import geometries.Intersectable;
import geometries.*;

import static org.junit.jupiter.api.Assertions.*;

class CameraRayIntersectionsIntegrationTest {
    /**
     * Integration tests of Camera Ray construction with Ray-Sphere intersections
     */
    @Test
    public void cameraRaySphereIntegration() {
        Camera cam1 = new Camera(
                Point.ZERO,
                new Vector(0, 0, -1),
                new Vector(0, -1, 0));
        Camera cam2 = new Camera(
                new Point(0, 0, 0.5),
                new Vector(0, 0, -1),
                new Vector(0, -1, 0));

        // TC01: Small Sphere 2 points
        assertCountIntersections(cam1, new Sphere(new Point(0, 0, -3),1), 2);

        // TC02: Big Sphere 18 points
        assertCountIntersections(cam2, new Sphere(new Point(0, 0, -2.5),2.5), 18);

        // TC03: Medium Sphere 10 points
        assertCountIntersections(cam2, new Sphere(new Point(0, 0, -2),2), 10);

        // TC04: Inside Sphere 9 points
        assertCountIntersections(cam2, new Sphere(new Point(0, 0, -1),4), 9);

        // TC05: Beyond Sphere 0 points
        assertCountIntersections(cam1, new Sphere(new Point(0, 0, 1),0.5), 0);
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Plane intersections
     */
    @Test
    public void cameraRayPlaneIntegration() {
        Camera cam = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Plane against camera 9 points
        assertCountIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 0, 1)), 9);

        // TC02: Plane with small angle 9 points
        assertCountIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 1, 2)), 9);

        // TC03: Plane parallel to lower rays 6 points
        assertCountIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 1, 1)), 6);

        // TC04: Beyond Plane 0 points
        assertCountIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 1, 1)), 6);
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Triangle intersections
     */
    @Test
    public void cameraRayTriangleIntegration() {
        Camera cam = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Small triangle 1 point
        assertCountIntersections(cam, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -1, -2)), 1);

        // TC02: Medium triangle 2 points
        assertCountIntersections(cam, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -20, -2)), 2);
    }

    /**
     * help function to test intersections with all kind of geometries
     * @param cam the camera we send rays from
     * @param geo the geometry
     * @param expected tha expected number of intersections with the geometry
     */
    private void assertCountIntersections(Camera cam, Intersectable geo, int expected) {
        cam.setViewPlaneSize(3,3).setDistance(1);

        int nX = 3, nY = 3, count = 0;

        for(int i = 0; i < nX; i++){
            for(int j = 0; j < nY; j++){
                var ray = cam.constructRayThroughPixel(nX, nY, j, i); // create ray in the view plane
                var intersections = geo.findIntersections(ray);

                if(intersections != null){
                    count += intersections.size(); // count the total number of point
                }
            }
        }
        assertEquals(expected, count, "The number of intersections found was incorrect");
    }
}
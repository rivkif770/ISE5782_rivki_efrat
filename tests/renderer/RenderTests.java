package renderer;


import geometries.Geometry;
import lighting.AmbientLight;
import geometries.Sphere;
import geometries.Triangle;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.*;

/**
 * Test rendering a basic image
 *
 * @author Dan
 */
public class RenderTests {
    /**
     * Produce a scene with basic 3D model and render it into a png image with a
     * grid
     */
    @Test
    public void basicRenderTwoColorTest() {
        Scene scene = new Scene("Test scene")//
                .setAmbientLight(new AmbientLight(new Color(255, 191, 191), //
                        new Double3(1, 1, 1))) //
                .setBackground(new Color(75, 127, 90));

        scene.geometries.add(new Sphere(new Point(0, 0, -100), 50d),
                new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
                // left
                new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100)), // down
                // left
                new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
        // right
        Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPDistance(100) //
                .setVPSize(500, 500) //
                .setImageWriter(new ImageWriter("base render test", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene));

        camera.renderImage();
        camera.printGrid(100, new Color(YELLOW));
        camera.writeToImage();
    }

    // For stage 6 - please disregard in stage 5
    /**
     * Produce a scene with basic 3D model - including individual lights of the
     * bodies and render it into a png image with a grid
     */
    @Test
    public void basicRenderMultiColorTest() {
        Scene scene = new Scene("Test scene")//
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2))); //

        scene.geometries.add( //
                new Sphere(new Point(0, 0, -100), 50),
                // up left
                new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100))
                        .setEmission(new Color(GREEN)),
                // down left
                new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100))
                        .setEmission(new Color(RED)),
                // down right
                new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))
                        .setEmission(new Color(BLUE)));

        Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPDistance(100) //
                .setVPSize(500, 500) //
                .setImageWriter(new ImageWriter("color render test", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene));

        camera.renderImage();
        camera.printGrid(100, new Color(WHITE));
        camera.writeToImage();
    }

    /**
     * Test for XML based scene - for bonus
     */
    @Test
    public void basicRenderXml() {
        Scene scene = new Scene("XML Test scene");
        // enter XML file name and parse from XML file into scene object
        // ...

        Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPDistance(100) //
                .setVPSize(500, 500).setImageWriter(new ImageWriter("xml render test", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene));
        camera.renderImage();
        camera.printGrid(100, new Color(YELLOW));
        camera.writeToImage();
    }

    /**
     * Test rendering a basic image
     *
     * @author Dan
     */
    public static class LightsTests {
        private Scene scene1 = new Scene("Test scene");
        private Scene scene2 = new Scene("Test scene") //
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));
        private Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150) //
                .setVPDistance(1000);
        private Camera camera2 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200) //
                .setVPDistance(1000);

        private Point[] p = { // The Triangles' vertices:
                new Point(-110, -110, -150), // the shared left-bottom
                new Point(95, 100, -150), // the shared right-top
                new Point(110, -110, -150), // the right-bottom
                new Point(-75, 78, 100) }; // the left-top
        private Point trPL = new Point(30, 10, -100); // Triangles test Position of Light
        private Point spPL = new Point(-50, -50, 25); // Sphere test Position of Light
        private Color trCL = new Color(800, 500, 250); // Triangles test Color of Light
        private Color spCL = new Color(800, 500, 0); // Sphere test Color of Light
        private Vector trDL = new Vector(-2, -2, -2); // Triangles test Direction of Light
        private Material material = new Material().setkD(0.5).setkS(0.5).setnShininess(300);
        private Geometry triangle1 = new Triangle(p[0], p[1], p[2]).setMaterial(material);
        private Geometry triangle2 = new Triangle(p[0], p[1], p[3]).setMaterial(material);
        private Geometry sphere = new Sphere(new Point(0, 0, -50), 50d) //
                .setEmission(new Color(BLUE).reduce(2)) //
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300));

        /**
         * Produce a picture of a sphere lighted by a directional light
         */
        @Test
        public void sphereDirectional() {
            scene1.geometries.add(sphere);
            scene1.lighting.add(new DirectionalLight(spCL, new Vector(1, 1, -0.5)));

            ImageWriter imageWriter = new ImageWriter("lightSphereDirectional", 500, 500);
            camera1.setImageWriter(imageWriter) //
                    .setRayTracer(new RayTracerBasic(scene1)) //
                    .renderImage() //
                    .writeToImage(); //
        }

        /**
         * Produce a picture of a sphere lighted by a point light
         */
        @Test
        public void spherePoint() {
            scene1.geometries.add(sphere);
            scene1.lighting.add(new PointLight(spCL, spPL).setkL(0.001).setkQ(0.0002));

            ImageWriter imageWriter = new ImageWriter("lightSpherePoint", 500, 500);
            camera1.setImageWriter(imageWriter) //
                    .setRayTracer(new RayTracerBasic(scene1)) //
                    .renderImage() //
                    .writeToImage(); //
        }

        /**
         * Produce a picture of a sphere lighted by a spot light
         */
        @Test
        public void sphereSpot() {
            scene1.geometries.add(sphere);
            scene1.lighting.add(new SpotLight(spCL, spPL, new Vector(1, 1, -0.5)).setkL(0.001).setkQ(0.0001));

            ImageWriter imageWriter = new ImageWriter("lightSphereSpot", 500, 500);
            camera1.setImageWriter(imageWriter) //
                    .setRayTracer(new RayTracerBasic(scene1)) //
                    .renderImage() //
                    .writeToImage(); //
        }

        /**
         * Produce a picture of a two triangles lighted by a directional light
         */
        @Test
        public void trianglesDirectional() {
            scene2.geometries.add(triangle1, triangle2);
            scene2.lighting.add(new DirectionalLight(trCL, trDL));

            ImageWriter imageWriter = new ImageWriter("lightTrianglesDirectional", 500, 500);
            camera2.setImageWriter(imageWriter) //
                    .setRayTracer(new RayTracerBasic(scene2)) //
                    .renderImage() //
                    .writeToImage(); //
        }

        /**
         * Produce a picture of a two triangles lighted by a point light
         */
        @Test
        public void trianglesPoint() {
            scene2.geometries.add(triangle1, triangle2);
            scene2.lighting.add(new PointLight(trCL, trPL).setkL(0.001).setkQ(0.0002));

            ImageWriter imageWriter = new ImageWriter("lightTrianglesPoint", 500, 500);
            camera2.setImageWriter(imageWriter) //
                    .setRayTracer(new RayTracerBasic(scene2)) //
                    .renderImage() //
                    .writeToImage(); //
        }

        /**
         * Produce a picture of a two triangles lighted by a spot light
         */
        @Test
        public void trianglesSpot() {
            scene2.geometries.add(triangle1, triangle2);
            scene2.lighting.add(new SpotLight(trCL, trPL, trDL).setkL(0.001).setkQ(0.0001));

            ImageWriter imageWriter = new ImageWriter("lightTrianglesSpot", 500, 500);
            camera2.setImageWriter(imageWriter) //
                    .setRayTracer(new RayTracerBasic(scene2)) //
                    .renderImage() //
                    .writeToImage(); //
        }

        /**
         * Produce a picture of a sphere lighted by a narrow spot light
         */
        @Test
        public void sphereSpotSharp() {
            scene1.geometries.add(sphere);
            scene1.lighting
                    .add(new SpotLight(spCL, spPL, new Vector(1, 1, -0.5)).setNarrowBeam(10).setkL(0.001).setkQ(0.00004));

            ImageWriter imageWriter = new ImageWriter("lightSphereSpotSharp", 500, 500);
            camera1.setImageWriter(imageWriter) //
                    .setRayTracer(new RayTracerBasic(scene1)) //
                    .renderImage() //
                    .writeToImage(); //
        }

        /**
         * Produce a picture of a two triangles lighted by a narrow spot light
         */
        @Test
        public void trianglesSpotSharp() {
            scene2.geometries.add(triangle1, triangle2);
            scene2.lighting.add(new SpotLight(trCL, trPL, trDL).setNarrowBeam(10).setkL(0.001).setkQ(0.00004));

            ImageWriter imageWriter = new ImageWriter("lightTrianglesSpotSharp", 500, 500);
            camera2.setImageWriter(imageWriter) //
                    .setRayTracer(new RayTracerBasic(scene2)) //
                    .renderImage() //
                    .writeToImage(); //
        }

    }
}



package renderer;

import org.junit.jupiter.api.Test;

import lighting.*;
import geometries.*;
import primitives.*;
import scene.Scene;
import static java.awt.Color.*;

/**
 * Test rendering a basic image
 *
 * @author Dan
 */
public class LightsTests {
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
    private Point spCLA = new Point(-50, -50, 25); // Sphere test Color of Light
    private Point spCLR = new Point(10, -40, 40); // Sphere test Color of Light
    private Point spCLE = new Point(-20, 10, 50); // Sphere test Color of Light
    private Point spPL = new Point(-50, -50, 25); // Sphere test Position of Light
    private Color trCL = new Color(800, 500, 250); // Triangles test Color of Light
    private Color spCL = new Color(800, 500, 0); // Sphere test Color of Light
    private Color spCLF = new Color(800, 500, 0); // Sphere test Color of Light
    private Color spCLG = new Color(800, 150, 0); // Sphere test Color of Light
    private Color spCLM = new Color(100, 500, 0); // Sphere test Color of Light
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
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void sphereSpotAndPoint() {
        scene1.geometries.add(sphere);
        scene1.lighting.add(new SpotLight(spCLG, spCLR, new Vector(-1, 3, -0.5)).setNarrowBeam(15).setkL(0.001).setkQ(0.0001));
        scene1.lighting.add(new SpotLight(spCLF, spCLA, new Vector(-1, 1, -0.5)).setkL(0.001).setkQ(0.0001));
//        scene1.lighting.add(new PointLight(spCL, spCLE).setkL(0.001).setkQ(0.0002));
        scene1.lighting.add(new DirectionalLight(spCL, new Vector(1, 1, -0.5)));
        scene1.lighting.add(new DirectionalLight(spCLM, new Vector(1, -1, -0.5)));
        ImageWriter imageWriter = new ImageWriter("lightSphereSpotAndPoint", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage() //
                .writeToImage(); //
    }
    @Test
    public void trianglSpotAndPoint() {
        scene2.geometries.add(triangle1, triangle2);
        scene2.lighting.add(new SpotLight(new Color(255, 0, 0), trPL, new Vector(-5, -2, -2)).setNarrowBeam(10).setkL(0.0001).setkQ(0.00004));
        scene2.lighting.add(new SpotLight(new Color(0, 0, 255), trPL, new Vector(10, 2, -2)).setNarrowBeam(10).setkL(0.0001).setkQ(0.00004));
        scene2.lighting.add(new SpotLight(new Color(0, 255, 0), trPL, new Vector(10, -2, -2)).setNarrowBeam(10).setkL(0.0001).setkQ(0.00004));
        scene2.lighting.add(new PointLight(new Color(800, 500, 250), new Point(30, -10, -100)).setkL(0.001).setkQ(0.0002));
        ImageWriter imageWriter = new ImageWriter("lightTrianglesSpotSharp", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
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
    @Test
    void testSpotLightWithFocus(){
        scene2.geometries.add(triangle1, triangle2);
        scene2.lighting.add(new SpotLight(
                new Color(800, 400, 400),
                new Point(10, -10, -130),
                new Vector(-2, -2, -1)).setNarrowBeam(12)
                .setkC(1).setkL(0.0000025).setkQ(0.00005));

        ImageWriter imageWriter = new ImageWriter("trianglesSpotSharpFocus", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2))
                .renderImage()
                .writeToImage();
    }

    @Test
    public void sphereSpotFocus() {
        scene1.geometries.add(sphere
                .setEmission(new Color(148, 183, 176))
                .setMaterial(sphere.getMaterial().setnShininess(150)));
        scene1.lighting.add(new SpotLight(
                new Color(500, 300, 0), new Point(-50, -50, 50), new Vector(1, 1, -2))
                .setNarrowBeam(50)
                .setkC(1).setkL(0.00000001).setkQ(0.00001));

        ImageWriter imageWriter = new ImageWriter("sphereSpotWithFocus", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1));
        camera1.renderImage();
        camera1.writeToImage();
    }

}
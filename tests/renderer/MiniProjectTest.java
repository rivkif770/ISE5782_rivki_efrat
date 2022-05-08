package renderer;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;
import scene.Scene;

import static java.awt.Color.*;

public class MiniProjectTest {
    private Scene scene = new Scene("Test scene11");
    @Test
    public void specialPicture() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);
        scene.background=new Color(124,242,248);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));
        scene.geometries.add( //
                new Elepsoaide(new Point(50,40,0),10,17,50).setEmission(new Color(PINK))
                .setMaterial(new Material().setkR(1d)),
                new Elepsoaide(new Point(30,40,0),10,17,50).setEmission(new Color(RED))
                .setMaterial(new Material().setkR(1d)),
                 new Elepsoaide(new Point(50,55,20),10,17,50).setEmission(new Color(BLACK))
                .setMaterial(new Material().setkR(1d)),
                 new Elepsoaide(new Point(40,35,10),10,17,50).setEmission(new Color(BLUE))
                .setMaterial(new Material().setkR(1d)),
                 new Elepsoaide(new Point(60,30,0),10,17,50).setEmission(new Color(blue))
                .setMaterial(new Material().setkR(1d)));
                new Triangle(new Point(1500, -1500, 5000), new Point(-1500, 1500, 5000),
                new Point(-1500, -1500, 5000))
                        .setEmission(new Color(black)) //
                        .setMaterial(new Material().setkR(0.5));
              new Triangle(new Point(-10, -60, 302), new Point(0, 0, 302), new Point(10, -60, 302))
                        .setEmission(new Color(black))//
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60).setkT(0.6));
//                new Triangle(new Point(-50, -50, 0), new Point(-50, 0, 0), new Point(-100, -100, 50))
//                        .setEmission(new Color(20,20,20))//
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60).setkT(0.6)),//
//                new Sphere(new Point(0, -20, 100), 40d).setEmission(new Color(BLUE)) //
//                        .setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
//                new Sphere(new Point(0, -20, 100), 15d).setEmission(new Color(RED)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)));
        scene.lighting.add(new SpotLight(new Color(1020, 400, 400), new Point(50, 40, 0), new Vector(-1, -1, -4)) //
                .setkL(0.00001).setkQ(0.000005));
        ImageWriter imageWriter = new ImageWriter("MiniProjectTest", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();

    }
}

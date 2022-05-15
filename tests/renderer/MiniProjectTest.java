package renderer;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;
import scene.Scene;

import static java.awt.Color.*;

public class MiniProjectTest {
    Point A=new Point(34.82, -19.69, 0);
    Point B=new Point(37.42, 14.15, 0);
    Point C=new Point(34.81, -0.78, 19.7);
    Point D=new Point(24.73, 21.56, -22.88);
    Point E=new Point(20.3, 34.47, 0);
    Point F=new Point(6, 34.07, 20.08);
    Point G=new Point(-12.33, 38.05, 0);
    Point H=new Point(-32.87, 22.8, 0);
    Point I=new Point(-19.97, 27.82, -20.67);
    Point J=new Point(-32.98, 7.5, 19.72);
    Point K=new Point(-39.19, -8.01, 0);
    Point L=new Point(-21.35, -33.82, 0);
    Point M=new Point(-25.51, -22.87, -20.65);
    Point N=new Point(-6.91, -33.61, 20.56);
    Point O=new Point(9.83, -38.77, 0);
    Point P=new Point(21.72, -24.47, -23.02);
    Point Q=new Point(-19.02, -12.86, 32.76);
    Point R=new Point(-8.94, 12.57, 36.91);
    Point S=new Point(10.55, -13.08, 36.3);
    Point T=new Point(15.82, 10.11, 35.32);
    Point U=new Point(15.96, -1.38, -36.65);
    Point V=new Point(0, -20.62, -34.27);
    Point W=new Point(0, 19.69, -34.82);
    Point Z=new Point(-14.93, 0.49, -37.1);

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
    @Test
    public void specialPicture1() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));
        scene.background = new Color(black);
        scene.geometries.add( //
//                new Sphere(new Point(0, 0, 0), 40).setEmission(new Color(128,128,128))
//                       .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)),
                new Triangle(A,B,C)
                       .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.9999)),
                new Triangle(B,E,D)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.9999)),
                new Triangle(E,F,G)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.9999)),
                new Triangle(G,H,I)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.9999)),
                new Triangle(H,J,K)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.9999)),
                new Triangle(K,L,M)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.9999)),
                new Triangle(L,N,O)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.9999)),
                new Triangle(O,A,P)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.9999)),
                new Triangle(J,Q,R)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.9999)),
                new Triangle(N,Q,S)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.9999)),
                new Triangle(C,S,T)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.9999)),
                new Triangle(F,T,R)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.9999)),
                new Triangle(P,U,V)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.9999)),
                new Triangle(D,U,W)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.9999)),
                new Triangle(M,V,Z)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.9999)),
                new Triangle(I,Z,W)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.6)),
                new Plane(new Point(0,-50,0),new Point(1,-50,0),new Point(0,-50,1))
                        .setMaterial(new Material().setkR(0.5)));
        scene.lighting.add(new DirectionalLight(new Color(50,1500,400), new Vector(-1, 1, 0)));
//        scene.lighting.add(new SpotLight(new Color(0, 400, 8000), new Point(60, 50, 20), new Vector(-1, 1, 0)) //
//                .setkL(0.00001).setkQ(0.000005));
        scene.lighting.add(new SpotLight(new Color(0, 400, 8000), new Point(60, -50, 20), new Vector(1, 1, 0)) //
                .setkL(0.00001).setkQ(0.000005));
        ImageWriter imageWriter = new ImageWriter("MiniProjectTest1", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }
}

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
    Point A=new Point(-51.82, -19.69, -550);
    Point B=new Point(-48.42, 14.15, -550);
    Point C=new Point(-51.81, -0.78, -530.3);
    Point D=new Point(-61.73, 21.56, -672.88);
    Point E=new Point(-65.3, 34.47, -550);
    Point F=new Point(-79, 34.07, -529.92);
    Point G=new Point(-97.33, 38.05, -550);
    Point H=new Point(-117.87, 22.8, -550);
    Point I=new Point(-104.97, 27.82, -570.67);
    Point J=new Point(-117.98, 7.5, -530.28);
    Point K=new Point(-124.19, -8.01, -550);
    Point L=new Point(-106.35, -33.82, -550);
    Point M=new Point(-110.51, -22.87, -570.65);
    Point N=new Point(-91.91, -33.61, -529.44);
    Point O=new Point(-76.83, -38.77, -550);
    Point P=new Point(-64.72, -24.47, -573.02);
    Point Q=new Point(-104.02, -12.86, -517.24);
    Point R=new Point(-93.94, 12.57, -513.09);
    Point S=new Point(-75.55, -13.08, -486.7);
    Point T=new Point(-70.82, 10.11, -514.68);
    Point U=new Point(-70.96, -1.38, -586.65);
    Point V=new Point(-85, -20.62, -584.27);
    Point W=new Point(-85, 19.69, -584.82);
    Point Z=new Point(-99.93, 0.49, -587.1);

    private Scene scene = new Scene("Test scene11");
    //@Test
//    public void specialPicture() {
//        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
//                .setVPSize(200, 200).setVPDistance(1000);
//        scene.background=new Color(124,242,248);
//        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));
//        scene.geometries.add( //
//                new Elepsoaide(new Point(50,40,0),10,17,50).setEmission(new Color(PINK))
//                .setMaterial(new Material().setkR(1d)),
//                new Elepsoaide(new Point(30,40,0),10,17,50).setEmission(new Color(RED))
//                .setMaterial(new Material().setkR(1d)),
//                 new Elepsoaide(new Point(50,55,20),10,17,50).setEmission(new Color(BLACK))
//                .setMaterial(new Material().setkR(1d)),
//                 new Elepsoaide(new Point(40,35,10),10,17,50).setEmission(new Color(BLUE))
//                .setMaterial(new Material().setkR(1d)),
//                 new Elepsoaide(new Point(60,30,0),10,17,50).setEmission(new Color(blue))
//                .setMaterial(new Material().setkR(1d)));
//                new Triangle(new Point(1500, -1500, 5000), new Point(-1500, 1500, 5000),
//                new Point(-1500, -1500, 5000))
//                        .setEmission(new Color(black)) //
//                        .setMaterial(new Material().setkR(0.5));
//              new Triangle(new Point(-10, -60, 302), new Point(0, 0, 302), new Point(10, -60, 302))
//                        .setEmission(new Color(black))//
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60).setkT(0.6));
//
////                new Triangle(new Point(-50, -50, 0), new Point(-50, 0, 0), new Point(-100, -100, 50))
////                        .setEmission(new Color(20,20,20))//
////                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60).setkT(0.6)),//
////                new Sphere(new Point(0, -20, 100), 40d).setEmission(new Color(BLUE)) //
////                        .setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
////                new Sphere(new Point(0, -20, 100), 15d).setEmission(new Color(RED)) //
////                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)));
//        scene.lighting.add(new SpotLight(new Color(1020, 400, 400), new Point(50, 40, 0), new Vector(-1, -1, -4)) //
//                .setkL(0.00001).setkQ(0.000005));
//        ImageWriter imageWriter = new ImageWriter("MiniProjectTest", 500, 500);
//        camera.setImageWriter(imageWriter) //
//                .setRayTracer(new RayTracerBasic(scene)) //
//                .renderImage() //
//                .writeToImage();
//
//    }
    @Test
    public void specialPicture1() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(500, 500).setVPDistance(1000);
        //scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));
        scene.background = new Color(black);
        scene.geometries.add( //
//                new Sphere(new Point(0, 0, 0), 40).setEmission(new Color(128,128,128))
//                       .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)),
                new Triangle(A, B, C)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.5)),
                new Triangle(B, E, D)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.5)),
                new Triangle(E, F, G)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.5)),
                new Triangle(G, H, I)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.5)),
                new Triangle(H, J, K)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.5)),
                new Triangle(K, L, M)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.5)),
                new Triangle(L, N, O)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.5)),
                new Triangle(O, A, P)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.5)),
                new Triangle(J, Q, R)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.5)),
                new Triangle(N, Q, S)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.5)),
                new Triangle(C, S, T)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.5)),
                new Triangle(F, T, R)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.5)),
                new Triangle(P, U, V)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.5)),
                new Triangle(D, U, W)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.5)),
                new Triangle(M, V, Z)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.5)),
                new Triangle(I, Z, W)
                        .setEmission(new Color(red))//
                        .setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.5)),
                new Plane(new Point(0, -39, 0), new Point(1, -39, 0), new Point(0, -46, 100))
                        .setEmission(new Color(black))
                        .setMaterial(new Material().setkR(0.5)),
                new Polygon(new Point(-25, 60, 330),new Point(25, 60, 330),new Point(50, 0, 330),
                        new Point(25, -60, 330),new Point(-25, -60, 330),new Point(-50, 0, 330))
                        .setEmission(new Color(BLUE))
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(1.0)),
                new Sphere(new Point(0, -45, 300), 15)
                        .setEmission(new Color(190,45,200))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.1)),
                new Sphere(new Point(-52, -25, 120), 22)
                        .setEmission(new Color(117,250,141))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.1)),
                new Sphere(new Point(55, -24.5, 150), 25)
                        .setEmission(new Color(255,255,145))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(1.0)),
                new Sphere(new Point(0, 0, 0), 50)
                        .setEmission(new Color(BLUE))//
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.6)));
        scene.lighting.add(new DirectionalLight(new Color(50, 1500, 400), new Vector(-1, 1, 0)));
        scene.lighting.add(new SpotLight(new Color(700, 400, 400), new Point(0, 80, 30), new Vector(0, -1, -10)) //
                .setkL(4E-5).setkQ(2E-7));
//        scene.lighting.add(new SpotLight(new Color(0, 400, 8000), new Point(60, -50, 20), new Vector(1, 1, 0)) //
//                .setkL(4E-5).setkQ(2E-7));
        ImageWriter imageWriter = new ImageWriter("MiniProjectTest1", 1000, 1000);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage()
                //.moveCamera(new Point(0, 1000, 0),new Point(0, 0, 0))//
                .writeToImage();
    }
    @Test
    public void tryS() {
        Scene scene = new Scene("Test scene");
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));
        Camera camera = new Camera(new Point(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)) //
                .setVPSize(1000, 1000).setVPDistance(1000);
        scene.geometries.add(

                new Sphere(new Point(100, 0, -200),190)
                        .setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.8).setkR(0.0)) ,

                new Sphere(new Point(100, 0, -200),145)
                        .setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.8).setkR(0.0)) ,

                new Sphere(new Point(-205, 95, -200),95)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)),
                //region Chain
                new Sphere(new Point(-340, 115, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-339, 95, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-335, 75, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-330, 55, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-320, 37, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-310, 19, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-295, 5, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-280, -10, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-265, -20, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-245, -26, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-225, -31, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-205, -33, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-185, -30, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-165, -25, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-148, -17, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-132, -8, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-118, 5, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-108, 23, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-100, 40, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-95, 60, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-89, 79, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-79, 97, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-69, 115, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-57, 132, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-43, 149, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-27, 162, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(-12, 172, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(5, 182, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(22, 190, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(40, 196, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(60, 202, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(80, 204, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(100, 205, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(120, 205, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(140, 204, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(160, 200, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(180, 195, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(195, 185, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(212, 175, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(226, 166, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
                new Sphere(new Point(241, 154, -200),10)
                        .setEmission(new Color(GREEN))//
                        .setMaterial(new Material().setkD(0.8).setkS(0.8).setnShininess(30).setkT(0.0).setkR(0.0)) ,
//endregion

                new Triangle(new Point(500, 200, -100), new Point(-500, 200, -100), new Point(1800, 200, -700))
                        .setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setkD(0.8).setkS(1.0).setnShininess(10000).setkR(1d).setGlossy(0.9)),

                new Triangle(new Point(-500, 200, -100), new Point(1800, 200, -700), new Point(-1800, 200, -700))
                        .setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setkD(0.8).setkS(1d).setnShininess(10000).setkR(1d).setGlossy(0.9)));


        scene.lighting.add(new DirectionalLight(new Color(10, 10, 10), new Vector(1, -1, 0)));
        scene.lighting.add(new SpotLight(new Color(400, 400, 1020), new Point(-300, -300, -100), new Vector(2, 2, -3))
                        .setkL(0.00001).setkQ(0.000005).setkC(1));

        ImageWriter imageWriter = new ImageWriter("my picture", 1000, 1000);

        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage()
                //.moveCamera(new Point(0, 1000, 0),new Point(0, 0, 0))//
                .writeToImage();
        //render.renderImage();
        imageWriter.writeToImage();
    }
}
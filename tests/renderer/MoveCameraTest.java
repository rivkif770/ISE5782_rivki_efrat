
package renderer;

        import geometries.Sphere;
        import geometries.Triangle;
        import lighting.AmbientLight;
        import lighting.SpotLight;
        import org.junit.jupiter.api.Test;
        import primitives.*;
        import scene.Scene;

        import static java.awt.Color.*;

class CameraMoveTest {
    @Test
    public void CameraRotateTest(){
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);

        Scene scene=new Scene("Camera Move Test");
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));
        scene.geometries.add( //
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setkS(0.8).setnShininess(60)), //
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setkS(0.8).setnShininess(60)), //
                new Sphere(new Point(0, 0, -11), 30d) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)) //
        );
        scene.lighting.add( //
                new SpotLight(new Color(700, 400, 400), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
                        .setkL(4E-4).setkQ(2E-5));

        int frames = 10;
        double angle = 360d / frames;
        double angleRadians = 2 * Math.PI / frames;
        double radius = camera.getP0().subtract(Point.ZERO).length();

        for (int i = 0; i < frames; i++) {
            camera.rotate(0, angle, 0);
            camera.setP0(
                    Math.sin(angleRadians * (i + 1)) * radius,
                    0,
                    Math.cos(angleRadians * (i + 1)) * radius
            );

            camera.setImageWriter(new ImageWriter("moveTest" + (i + 1), 500, 500))
                    .setRayTracer(new RayTracerBasic(scene))
                    .renderImage();
            camera.writeToImage();
        }
    }
}
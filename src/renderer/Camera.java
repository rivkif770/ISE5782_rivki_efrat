package renderer;

import primitives.*;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.stream.IntStream;

import static primitives.Util.*;

/**
 * this class represent camera by location <br/>
 * and directions toward, right and up to the scene that lives in a virtual view plane. <br/>
 * The view plane is represent by height and wight
 * @author rivki and efrat
 */
public class Camera {

    private Point p0;
    private Vector vRight;
    private Vector vUp;
    private Vector vTo;
    private double distance;
    private double width;
    private double height;
    private Point centerPoint;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;
    private int antiAliasing=1;
    private boolean adaptive = false;

    /**
     * constructor for camera
     *
     * @param p0  the location of the camera
     * @param vTo the direction to the view plane
     * @param vUp the direction up
     * @throws IllegalArgumentException if vTo and vUp is not orthogonal
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (!isZero(vUp.dotProduct(vTo))) {
            throw new IllegalArgumentException("The vectors 'up' and and 'to' is not orthogonal");
        }

        this.p0 = p0;
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        vRight = this.vTo.crossProduct(this.vUp).normalize();
    }

    //region Getters/Setters
    /**
     * get of p0
     *
     * @return point
     */
    public Point getP0() {
        return p0;
    }

    /**
     * get of vRight
     *
     * @return vector
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * get of vUp
     *
     * @return vector
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * get of vTo
     *
     * @return vector
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * get of distance
     *
     * @return double
     */
    public double getDistance() {
        return distance;
    }

    /**
     * get of width
     *
     * @return double
     */
    public double getWidth() {
        return width;
    }

    /**
     * get of height
     *
     * @return double
     */
    public double getHeight() {
        return height;
    }

    /**
     * get of centerPoint
     *
     * @return point
     */
    public Point get_centerVPPoint() {
        return centerPoint;
    }

    /**
     * set the view plane size
     *
     * @param width  the width of the view plane
     * @param height the height of the view plane
     * @return this camera (like builder pattern)
     * @throws IllegalArgumentException if width or height equal to 0
     */
    public Camera setVPSize(double width, double height) {
        if (isZero(width) || isZero(height)) {
            throw new IllegalArgumentException("width or height cannot be zero");
        }

        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * set the distance from the camera to the view plane
     *
     * @param distance the distance
     * @return this camera (like builder pattern)
     * @throws IllegalArgumentException if distance = 0
     */
    public Camera setVPDistance(double distance) {
        if (isZero(distance)) {
            throw new IllegalArgumentException("distance cannot be zero");
        }

        this.distance = distance;
        // every time that we chang the distance from the view plane
        // we will calculate the center point of the view plane aging
        centerPoint = p0.add(vTo.scale(this.distance));
        return this;
    }
    /**
     * set the rayTracer ray from the camera to the view plane
     *
     * @param rayTracer the rayTracer
     * @return this camera (like builder pattern)
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return  this;
    }

    /**
     * set the imageWriter  for the Camera
     *
     * @return the Camera object
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }
    /**
     * set the anti Aliasing
     *
     * @return the Camera object
     */
    public Camera SetantiAliasing(int antiAliasing) {
        this.antiAliasing = antiAliasing;
        return this;
    }
    /**
     * set the adaptive
     *
     * @return the Camera object
     */
    public Camera Setadaptive(boolean adaptive) {
        this.adaptive = adaptive;
        return this;
    }
    /**
     * set senter the camera
     *
     */
    public void setP0(double x,double y, double z) {
        this.p0=new Point(x,y,z);
    }
//endregion

    /**
     * Checks that all fields are full and creates an image
     */
//    public Camera renderImage() {
//        if (p0 == null || vRight == null
//                || vUp == null || vTo == null || distance == 0
//                || width == 0 || height == 0 || centerPoint == null
//                || imageWriter == null || rayTracer == null) {
//            throw new MissingResourceException("Missing camera data", Camera.class.getName(), null);
//        }
//        for (int i = 0; i < imageWriter.getNy(); i++) {
//            for (int j = 0; j < imageWriter.getNx(); j++) {
//                // Pixel coloring by ray
//                List<Ray> rays = constructRays(imageWriter.getNx(), imageWriter.getNy(), j, i,antiAliasing);
//                imageWriter.writePixel(j,i, rayTracer.TraceRays(rays));
//
//            }
//        }
//        return this;
//    }

//    /**
//     * Checks that all fields are full and creates an image
//     */
//    public Camera renderImage() {
//        if (p0 == null || vRight == null
//                || vUp == null || vTo == null || distance == 0
//                || width == 0 || height == 0 || centerPoint == null
//                || imageWriter == null || rayTracer == null) {
//            throw new MissingResourceException("Missing camera data", Camera.class.getName(), null);
//        }
//        Pixel.initialize(imageWriter.getNy(),imageWriter.getNx() , 1);
//        IntStream.range(0, imageWriter.getNy()).parallel().forEach(i -> {
//            IntStream.range(0, imageWriter.getNx()).parallel().forEach(j -> {
//                // Pixel coloring by ray
//                List<Ray> rays = constructRays(imageWriter.getNx(), imageWriter.getNy(), j, i,antiAliasing);
//                imageWriter.writePixel(j,i, rayTracer.TraceRays(rays));
//                Pixel.pixelDone();
//                Pixel.printPixel();
//
//            });
//        });
//        return this;
//    }
    public Camera renderImage() {
        if (p0 == null || vRight == null
                || vUp == null || vTo == null || distance == 0
                || width == 0 || height == 0 || centerPoint == null
                || imageWriter == null || rayTracer == null) {
            throw new MissingResourceException("Missing camera data", Camera.class.getName(), null);
        }
        int threadsCount = 3;
        Pixel.initialize(imageWriter.getNy(), imageWriter.getNx(), 1);


        if (!adaptive) {
            while (threadsCount-- > 0) {
                new Thread(() -> {
                    for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone())
                        imageWriter.writePixel(pixel.col, pixel.row, rayTracer.TraceRays(constructRays(imageWriter.getNx(), imageWriter.getNy(), pixel.col, pixel.row, antiAliasing)));
                }).start();
            }
            Pixel.waitToFinish();
        } else {
            while (threadsCount-- > 0) {
                new Thread(() -> {
                    for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone())
                        imageWriter.writePixel(pixel.col, pixel.row, AdaptiveSuperSampling(imageWriter.getNx(), imageWriter.getNy(), pixel.col, pixel.row, antiAliasing));
                }).start();
            }
            Pixel.waitToFinish();
        }
        return this;
    }

    /**
     * Checks the color of the pixel with the help of individual rays and averages between them and only
     * if necessary continues to send beams of rays in recursion
     * @param nX Pixel length
     * @param nY Pixel width
     * @param j The position of the pixel relative to the y-axis
     * @param i The position of the pixel relative to the x-axis
     * @param numOfRays The amount of rays sent
     * @return Pixel color
     */
    private Color AdaptiveSuperSampling(int nX, int nY, int j, int i,  int numOfRays)  {
        Vector Vright = vRight;
        Vector Vup = vUp;
        Point cameraLoc = this.getP0();
        int numOfRaysInRowCol = (int)Math.floor(Math.sqrt(numOfRays));
        if(numOfRaysInRowCol == 1)  return rayTracer.TraceRay(constructRayThroughPixel(nX, nY, j, i));

        Point pIJ = getCenterOfPixel(nX, nY, j, i);

        double rY = alignZero(height / nY);
        // the ratio Rx = w/Nx, the width of the pixel
        double rX = alignZero(width / nX);


        double PRy = rY/numOfRaysInRowCol;
        double PRx = rX/numOfRaysInRowCol;
        return rayTracer.AdaptiveSuperSamplingRec(pIJ, rX, rY, PRx, PRy,cameraLoc,Vright, Vup,null);
    }

    /**
     * Checks that all fields are full and creates an image
     */
//    public Camera renderImage() {
//        if (p0 == null || vRight == null
//                || vUp == null || vTo == null || distance == 0
//                || width == 0 || height == 0 || centerPoint == null
//                || imageWriter == null || rayTracer == null) {
//            throw new MissingResourceException("Missing camera data", Camera.class.getName(), null);
//        }
//        int threadsCount = 3;
//        Pixel.initialize(imageWriter.getNy(),imageWriter.getNx() , 1);
//
//        while (threadsCount-- > 0) {
//            new Thread(() -> {
//                for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone())
//                    imageWriter.writePixel(pixel.col,pixel.row, rayTracer.TraceRays(constructRays(imageWriter.getNx(), imageWriter.getNy(), pixel.col, pixel.row,antiAliasing)));
//            }).start();
//        }
//        Pixel.waitToFinish();
//
//
//        return this;
//    }

    /**
     *Grid printing
     * @param interval The space between pixels
     * @param color color of grid
     */
    public void printGrid(int interval, Color color) {
        if (this.imageWriter == null)
            throw new MissingResourceException("imageWriter is null", ImageWriter.class.getName(), null);
        imageWriter.printGrid(interval,color);
    }

    /**
     * construct ray through a pixel in the view plane
     * nX and nY create the resolution
     *
     * @param nX number of pixels in the width of the view plane
     * @param nY number of pixels in the height of the view plane
     * @param j  index row in the view plane
     * @param i  index column in the view plane
     * @return ray that goes through the pixel (j, i)  Ray(p0, Vi,j)
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        Point pIJ = getCenterOfPixel(nX, nY, j, i); // center point of the pixel

        //Vi,j = Pi,j - P0, the direction of the ray to the pixel(j, i)
        Vector vIJ = pIJ.subtract(p0);
        return new Ray(p0, vIJ);
    }

    /**
     * get the center point of the pixel in the view plane
     *
     * @param nX number of pixels in the width of the view plane
     * @param nY number of pixels in the height of the view plane
     * @param j  index row in the view plane
     * @param i  index column in the view plane
     * @return the center point of the pixel
     */
    private Point getCenterOfPixel(int nX, int nY, int j, int i) {
        // calculate the ratio of the pixel by the height and by the width of the view plane
        // the ratio Ry = h/Ny, the height of the pixel
        double rY = alignZero(height / nY);
        // the ratio Rx = w/Nx, the width of the pixel
        double rX = alignZero(width / nX);

        // Xj = (j - (Nx -1)/2) * Rx
        double xJ = alignZero((j - ((nX - 1d) / 2d)) * rX);
        // Yi = -(i - (Ny - 1)/2) * Ry
        double yI = alignZero(-(i - ((nY - 1d) / 2d)) * rY);

        Point pIJ = centerPoint;

        if (!isZero(xJ)) {
            pIJ = pIJ.add(vRight.scale(xJ));
        }
        if (!isZero(yI)) {
            pIJ = pIJ.add(vUp.scale(yI));
        }
        return pIJ;
    }

    /**
     * Creates a beam of rays into a square grid
     * @param nX Pixel length
     * @param nY Pixel width
     * @param j Position the pixel on the y-axis inside the grid
     * @param i Position the pixel on the x-axis inside the grid
     * @param numOfRays The root of the number of beams sent per pixel
     * @return List of beams of rays
     */
    public List<Ray> constructRays(int nX, int nY, int j, int i, int numOfRays) {
        if (numOfRays== 0) {
            throw new IllegalArgumentException("num Of Rays can not be 0");
        }
        if (numOfRays == 1) {
            return List.of(constructRayThroughPixel(nX, nY, j, i));
        }
        else {
            List<Ray> rays = new LinkedList<>();
            Point pIJ = getCenterOfPixel(nX, nY, j, i);

            double rY = alignZero(height / nY);
            // the ratio Rx = w/Nx, the width of the pixel
            double rX = alignZero(width / nX);

            double pY = alignZero(rY / numOfRays);
            double pX = alignZero(rX / numOfRays);
            Point PijTemP = pIJ;
            for (int p = 1; p < numOfRays; p++) {
                for (int m = 1; m < numOfRays; m++) {
                    PijTemP = pIJ.add(vRight.scale(pX * m)).add(vUp.scale(pY * p));
                    rays.add(new Ray(p0, PijTemP.subtract(p0).normalize()));
                }
            }


            return rays;
        }

    }

    /**
     * Invites the coloring function
     */
    public void writeToImage() {
        imageWriter.writeToImage();
    }
//    /**
//     * Adds the given amount to the camera's position
//     *
//     * @return the current camera
//     */
//    public Camera move(Vector amount) {
//        p0 = p0.add(amount);
//        return this;
//    }
//
//    /**
//     * Adds x, y, z to the camera's position
//     *
//     * @return the current camera
//     */
//    public Camera move(double x, double y, double z) {
//        return move(new Vector(x, y, z));
//    }

//    /**
//     * Rotates the camera around the axes with the given angles
//     *
//     * @param amount vector of angles
//     * @return the current camera
//     */
//    public Camera rotate(Vector amount) {
//        return rotate(amount.getX(), amount.getY(), amount.getZ());
//    }
//
//
//    /**
//     * Rotates the camera around the axes with the given angles
//     *
//     * @param x angles to rotate around the x axis
//     * @param y angles to rotate around the y axis
//     * @param z angles to rotate around the z axis
//     * @return the current camera
//     */
//    public Camera rotate(double x, double y, double z) {
//        vTo = vTo.rotateX(x).rotateY(y).rotateZ(z);
//        vUp = vUp.rotateX(x).rotateY(y).rotateZ(z);
//        vRight = vTo.crossProduct(vUp);
//
//        return this;
//    }
//    /**
//     * moving the camera from her location
//     * @param newPosition the new position of the camera
//     * @param newPointOfView new point of view of the camera
//     * @return the new camera from the new position to the new point of view
//     */
//    public Camera moveCamera(Point newPosition, Point newPointOfView) {
//        // the new vTo of the the camera
//        Vector new_vTo = newPointOfView.subtract(newPosition).normalize();
//        // the angle between the new vTo and the old
//        double theta = new_vTo.dotProduct(vTo);
//        // axis vector for the rotation
//        Vector k = vTo.crossProduct(new_vTo).normalize();
//
//        vTo = new_vTo;
//        p0 = newPosition;
//
//        return rotateCamera(theta, k);
//    }
//    /**
//     * Rotate the camera by rotating the vectors of the camera directions <br/>
//     * According the Rodrigues' rotation formula
//     * @param theta angle theta according to the right hand rule in degrees
//     * @return this camera after the rotating
//     */
//    public Camera rotateCamera(double theta) {
//        return rotateCamera(theta, vTo);
//    }
//
//    /**
//     * Rotate the camera by rotating the vectors of the camera directions <br/>
//     * According the Rodrigues' rotation formula
//     * @param theta angle theta according to the right hand rule in degrees
//     * @param k axis vector for the rotation
//     * @return this camera after the rotating
//     */
//    private Camera rotateCamera(double theta, Vector k) {
//        double radianAngle = Math.toRadians(theta);
//        double cosTheta = alignZero(Math.cos(radianAngle));
//        double sinTheta = alignZero(Math.sin(radianAngle));
//
//        vRight.rotateVector(k, cosTheta, sinTheta);
//        vUp.rotateVector(k, cosTheta, sinTheta);
//
//        return this;
//    }
    /**
     * Adds the given amount to the camera's position
     *
     * @return the current camera
     */
    public Camera move(Vector amount) {
        p0 = p0.add(amount);
        return this;
    }

    /**
     * Adds x, y, z to the camera's position
     *
     * @return the current camera
     */
    public Camera move(double x, double y, double z) {
        return move(new Vector(x, y, z));
    }

    /**
     * Rotates the camera around the axes with the given angles
     *
     * @param amount vector of angles
     * @return the current camera
     */
    public Camera rotate(Vector amount) {
        return rotate(amount.getX(), amount.getY(), amount.getZ());
    }


    /**
     * Rotates the camera around the axes with the given angles
     *
     * @param x angles to rotate around the x axis
     * @param y angles to rotate around the y axis
     * @param z angles to rotate around the z axis
     * @return the current camera
     */
    public Camera rotate(double x, double y, double z) {
        vTo = vTo.rotateX(x).rotateY(y).rotateZ(z);
        vUp = vUp.rotateX(x).rotateY(y).rotateZ(z);
        vRight = vTo.crossProduct(vUp);

        return this;
    }
}

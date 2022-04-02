package renderer;

import primitives.*;
import scene.Scene;

import java.util.MissingResourceException;

import static primitives.Util.*;

public class Camera {

    private Point _p0;
    private Vector _vRight;
    private Vector _vUp;
    private Vector _vTo;
    private double _distance;
    private double _width;
    private double _height;
    private Point _centerPoint;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracerBase;

    private void renderImage() {
        if(_p0==null || _vRight==null ||_vUp==null ||_vTo==null ||_distance==0 ||_width==0 ||_height==0 ||_centerPoint==null ||imageWriter==null ||rayTracerBase==null )
        {
            throw new MissingResourceException("Missing camera data",);
        }
    }
    private void printGrid(int interval, Color color){
        if(this.imageWriter==null)
            throw new MissingResourceException(java.lang.String,"imageWriter is null");
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                // _width/interval
                if (i % interval == 0) {
                    imageWriter.writePixel(i, j, Color.RED);
                }
                // _height/interval
                else if (j % interval == 0) {
                    imageWriter.writePixel(i, j, Color.RED);
            }
        }
        imageWriter.writeToImage();
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
     * set the rayTracerBase  for the Camera
     *
     * @return the Camera object
     */
    public Camera setRayTracerBase(RayTracerBase rayTracerBase) {
        this.rayTracerBase = rayTracerBase;
        return this;
    }
    /**
     * get of p0
     * @return point
     */
    public Point get_p0() {
        return _p0;
    }
    /**
     * get of vRight
     * @return vector
     */
    public Vector get_vRight() {
        return _vRight;
    }
    /**
     * get of vUp
     * @return vector
     */
    public Vector get_vUp() {
        return _vUp;
    }
    /**
     * get of vTo
     * @return vector
     */
    public Vector get_vTo() {
        return _vTo;
    }
    /**
     * get of distance
     * @return double
     */
    public double get_distance() {
        return _distance;
    }
    /**
     * get of width
     * @return double
     */
    public double get_width() {
        return _width;
    }
    /**
     * get of height
     * @return double
     */
    public double get_height() {
        return _height;
    }
    /**
     * get of centerPoint
     * @return point
     */
    public Point get_centerVPPoint() {
        return _centerPoint;
    }

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

        _p0 = p0;
        _vUp = vUp.normalize();
        _vTo = vTo.normalize();
        _vRight = _vTo.crossProduct(_vUp).normalize();
    }
    /**
     * set the view plane size
     * @param width the width of the view plane
     * @param height the height of the view plane
     * @return this camera (like builder pattern)
     * @throws IllegalArgumentException if width or height equal to 0
     */
    public Camera setVPSize(double width, double height){
        if(isZero(width) || isZero(height)){
            throw new IllegalArgumentException("width or height cannot be zero");
        }

        _width = width;
        _height = height;
        return this;
    }
    /**
     * set the distance from the camera to the view plane
     * @param distance the distance
     * @return this camera (like builder pattern)
     * @throws IllegalArgumentException if distance = 0
     */
    public Camera setVPDistance(double distance){
        if(isZero(distance)){
            throw new IllegalArgumentException("distance cannot be zero");
        }

        _distance = distance;
        // every time that we chang the distance from the view plane
        // we will calculate the center point of the view plane aging
        _centerPoint = _p0.add(_vTo.scale(_distance));
        return this;
    }
    /**
     * construct ray through a pixel in the view plane
     * nX and nY create the resolution
     * @param nX number of pixels in the width of the view plane
     * @param nY number of pixels in the height of the view plane
     * @param j index row in the view plane
     * @param i index column in the view plane
     * @return ray that goes through the pixel (j, i)  Ray(p0, Vi,j)
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i){
        Point pIJ = getCenterOfPixel(nX, nY, j, i); // center point of the pixel

        //Vi,j = Pi,j - P0, the direction of the ray to the pixel(j, i)
        Vector vIJ = pIJ.subtract(_p0);
        return new Ray(_p0, vIJ);
    }
    /**
     * get the center point of the pixel in the view plane
     * @param nX number of pixels in the width of the view plane
     * @param nY number of pixels in the height of the view plane
     * @param j index row in the view plane
     * @param i index column in the view plane
     * @return the center point of the pixel
     */
    private Point getCenterOfPixel(int nX, int nY, int j, int i){
        // calculate the ratio of the pixel by the height and by the width of the view plane
        // the ratio Ry = h/Ny, the height of the pixel
        double rY = alignZero(_height / nY);
        // the ratio Rx = w/Nx, the width of the pixel
        double rX = alignZero(_width / nX);

        // Xj = (j - (Nx -1)/2) * Rx
        double xJ = alignZero((j - ((nX - 1d) / 2d)) * rX);
        // Yi = -(i - (Ny - 1)/2) * Ry
        double yI = alignZero(- (i - ((nY - 1d) / 2d)) * rY);

        Point pIJ = _centerPoint;

        if (xJ != 0d) {
            pIJ = pIJ.add(_vRight.scale(xJ));
        }
        if (yI != 0d) {
            pIJ = pIJ.add(_vUp.scale(yI));
        }
        return pIJ;
    }
}

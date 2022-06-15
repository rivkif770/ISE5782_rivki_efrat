package renderer;

import geometries.Geometry;
import geometries.Intersectable;
import primitives.*;
import lighting.*;
import java.lang.Math.*;
import geometries.Intersectable.GeoPoint;
import scene.Scene;

import static java.awt.Color.BLACK;
import static primitives.Util.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Rey tracer
 * calculate the color ray-geometry intersection
 * @author rivki and efrat
 */
public class RayTracerBasic extends RayTracerBase {
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = new Double3(1d);

    private int glossinessRaysNum = 36;
    private double distanceGrid = 25;
    private double sizeGrid=4;

    public void setDistanceGrid(double distanceGrid) {
        this.distanceGrid = distanceGrid;
    }

    /**
     * ctor - initializing the scene parameter
     * uses super ctor
     *
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }
    /**
     * Calculation of intensity of shadow on pixel's color with consideration of the transparency
     *
     * @param light    - Light source that effects the color and thus the shadow
     * @param l        - Vector from light source to point
     * @param n        - Normal to point
     * @param geopoint - The point of which the color is calculated
     * @return - The attenuation coefficient of light - determines the intensity of the shadow
     */
    private Double3 transparency(GeoPoint geopoint, LightSource light, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n); //build ray with delta
        double lightDistance = light.getDistance(geopoint.point);
        var intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return Double3.ONE; //no intersections
        Double3 ktr = new Double3(1d);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                ktr = ktr.product(gp.geometry.getMaterial().getkT()); //the more transparency the less shadow
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
            }
        }
        return ktr;
    }
    /**
     * According to the pong model
     * This model is additive in which we connect all the components that will eventually
     * make up an image with background colors, self-colors and texture colors.
     *
     * @param geoPoint the geometry and the lighted point at him
     * @param ray      the ray that goes out of the camera
     * @return the color at the point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }
    /**
     * overload methode of {@link renderer.RayTracerBasic#calcColor(GeoPoint, Ray)}
     *
     * @param geoPoint the geometry and the lighted point at him
     * @param ray      the ray that goes out of the camera
     * @param level    the level of the recursion
     * @param k        Represents influencing factors of transparency and reflection
     * @return the color at the point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = geoPoint.geometry.getEmission()
                .add(calcLocalEffects(geoPoint, ray, k));

        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));
    }
    /**
     * Computer lighting effects as at a certain point on geometry
     *
     * @param gp  the geometry and the lighted point at him
     * @param ray the ray from the camera
     * @return the total color at the point including the specular and diffusive
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        Color color = Color.BLACK;
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point); //normal to point
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) //vectors orthogonal - no effect
            return color;
        Material material = gp.geometry.getMaterial();

        for (LightSource lightSource : scene.lighting) { //sum of all effects of all lights on scene
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Double3 ktr = transparency(gp, lightSource, l, n); //intensity of shadow
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(iL.scale(calcDiffusive(material, nl)), iL.scale(calcSpecular(material, n, l, v)));
                }
            }
        }
        return color;
    }
    /**
     * Computer influences factors of transparency and reflection
     * @param gp the geometry and the lighted point at him
     * @param ray the ray that goes out of the camera
     * @param level the level of the recursion
     * @param k Represents influencing factors of transparency and reflection
     * @return the color with the transparency and reflection
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k){
        Color color = Color.BLACK;
        Material material = gp.geometry.getMaterial();
        Double3 kr = material.getkR();
        Double3 kkr = k.product(kr); //in each recursive iteration the impact of the reflection decreases
        Vector n = gp.geometry.getNormal(gp.point);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
            List<Ray> reflectedRays = constructReflectedRays(gp,ray, material.getGlossy());
            primitives.Color tempColor1 = primitives.Color.BLACK;
            // each ray
            for(Ray reflectedRay: reflectedRays)
            {
                GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
                tempColor1 = tempColor1.add(reflectedPoint == null ? primitives.Color.BLACK : calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
            }

            color = color.add(tempColor1.reduce(reflectedRays.size()));
        }
        Double3 kt = material.getkT();
        Double3 kkt = k.product(kt); //in each recursive iteration the impact of the refraction decreases
        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
            List<Ray> refractedRays = constructRefractedRays(gp,ray,n);
            primitives.Color tempColor2 = primitives.Color.BLACK;
            //calculate for each ray
            for(Ray refractedRay: refractedRays)
            {
                GeoPoint refractedPoint = findClosestIntersection(refractedRay);
                tempColor2 = tempColor2.add(refractedPoint == null? primitives.Color.BLACK : calcColor(refractedPoint, refractedRay, level -1, kkt).scale(kt));
            }

            color = color.add(tempColor2.reduce(refractedRays.size()));
        }
        return color;
    }
    /**  Produces a reflection bean that starts from
     * the point where the ray struck from the camera and goes diagonally to the point
     * @param geoPoint the point where the ray hit from the camera
     * @param ray the ray from the camera
     * @return a reflection ray
     */
    private List<Ray> constructReflectedRays(GeoPoint geoPoint, Ray ray, double Glossy) {
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(v.dotProduct(n));
        // r = v - 2*(v * n) * n
        Vector r = v.subtract(n.scale(2d * nv)).normalize();

        return raysGrid( new Ray(geoPoint.point,r,n),1,Glossy, n);
    }

    /**
     * Produces a transparency bean of rays that starts from
     * the point where the ray hit from the camera and
     * goes in the direction like the original ray
     * @param geoPoint the point where the ray hit from the camera
     * @param inRay the ray from the camera
     * @return transparency ray
     */
    private List<Ray> constructRefractedRays(GeoPoint geoPoint, Ray inRay, Vector n) {
        return raysGrid(new Ray(geoPoint.point, inRay.getDir(), n),-1,geoPoint.geometry.getMaterial().getGlossy(), n);
    }

    /**
     * Calculation of specular light component
     * @param material - Attenuation coefficient for specular light component
     * @param n - normal to point
     * @param l - direction vector from light to point
     * @param v - direction of ray shooted to point
     * @return Color - the calculated color of specular light component
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, Vector v) {
        Vector r = l.subtract(n.scale(2*l.dotProduct(n))).normalize();
        return material.kS.scale(Math.pow(Math.max(0,v.scale(-1).dotProduct(r)), material.nShininess));
    }
    /**
     * Calculation of diffusion light component
     * @param material - normal to point
     * @param nl - dot product between n-normal to point and l-direction vector from light to point
     * @return Color - the calculated color of diffusion light component
     */
    private Double3 calcDiffusive(Material material,double nl) {
        return material.kD.scale(Math.abs(nl));
    }

    /**
     * Trace the ray and calculates the color of the point that interact with the geometries of the scene
     * @param ray the ray that came out of the camera
     * @return the color of the object that the ray is interact with
     */
    @Override
    public Color TraceRay(Ray ray) {
        GeoPoint clossestGeoPoint = findClosestIntersection(ray);
        if (clossestGeoPoint == null)
            return scene.background;
        return calcColor(clossestGeoPoint, ray);
    }
    /**
     * Trace the ray and calculates the color of the point that interact with the geometries of the scene
     * @param rays the ray that came out of the camera
     * @return the color of the object that the ray is interact with
     */
    @Override
    public Color TraceRays(List<Ray> rays) {
        Color color=new Color(BLACK);
        for(Ray ray : rays)
        {
            GeoPoint clossestGeoPoint = findClosestIntersection(ray);
            if (clossestGeoPoint == null)
                color= color.add( scene.background);
            else color= color.add(calcColor(clossestGeoPoint,ray));
        }
        return color.reduce(rays.size());
    }

    /**
     * find closest intersection to the starting point of the ray
     * @param ray the ray that intersect with the geometries of the scene
     * @return the GeoPoint that is point is the closest point to the starting point of the ray
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        if(ray == null){
            return null;
        }

        List<GeoPoint> points = scene.geometries.findGeoIntersections(ray);
        return ray.findClosestGeoPoint(points);
    }

    /**
     * Building a beam of rays for transparency and reflection
     * @param ray The beam coming out of the camera
     * @param direction the vector
     * @param glossy The amount of gloss
     * @param n normal
     * @return Beam of rays
     */
    List<Ray> raysGrid(Ray ray, int direction, double glossy, Vector n){
        int numOfRowCol = isZero(glossy)? 1: (int)Math.ceil(Math.sqrt(glossinessRaysNum));
        if (numOfRowCol == 1) return List.of(ray);
        Vector Vup ;
        double Ax= Math.abs(ray.getDir().getX()), Ay= Math.abs(ray.getDir().getY()), Az= Math.abs(ray.getDir().getZ());
        if (Ax < Ay)
            Vup= Ax < Az ?  new Vector(0, -ray.getDir().getZ(), ray.getDir().getY()) :
                    new Vector(-ray.getDir().getY(), ray.getDir().getX(), 0);
        else
            Vup= Ay < Az ?  new Vector(ray.getDir().getZ(), 0, -ray.getDir().getX()) :
                    new Vector(-ray.getDir().getY(), ray.getDir().getX(), 0);
        Vector Vright = Vup.crossProduct(ray.getDir()).normalize();
        Point pc=ray.getPoint(distanceGrid);
        double step = glossy/sizeGrid;
        Point pij=pc.add(Vright.scale(numOfRowCol/2*-step)).add(Vup.scale(numOfRowCol/2*-step));
        Vector tempRayVector;
        Point Pij1;

        List<Ray> rays = new ArrayList<>();
        rays.add(ray);
        for (int i = 1; i < numOfRowCol; i++) {
            for (int j = 1; j < numOfRowCol; j++) {
                Pij1=pij.add(Vright.scale(i*step)).add(Vup.scale(j*step));
                tempRayVector =  Pij1.subtract(ray.getP0());
                if(n.dotProduct(tempRayVector) < 0 && direction == 1) //refraction
                    rays.add(new Ray(ray.getP0(), tempRayVector));
                if(n.dotProduct(tempRayVector) > 0 && direction == -1) //reflection
                    rays.add(new Ray(ray.getP0(), tempRayVector));
            }
        }

        return rays;
    }

    /**
     * Checks the color of the pixel with the help of individual rays and averages between
     * them and only if necessary continues to send beams of rays in recursion
     * @param centerP center pixl
     * @param Width Length
     * @param Height width
     * @param minWidth min Width
     * @param minHeight min Height
     * @param cameraLoc Camera location
     * @param Vright Vector right
     * @param Vup vector up
     * @param prePoints pre Points
     * @return Pixel color
     */
    @Override
    public Color AdaptiveSuperSamplingRec(Point centerP, double Width, double Height, double minWidth, double minHeight, Point cameraLoc, Vector Vright, Vector Vup, List<Point> prePoints) {
        if (Width < minWidth * 2 || Height < minHeight * 2) {
            return this.TraceRay(new Ray(cameraLoc, centerP.subtract(cameraLoc))) ;
        }

        List<Point> nextCenterPList = new LinkedList<>();
        List<Point> cornersList = new LinkedList<>();
        List<primitives.Color> colorList = new LinkedList<>();
        Point tempCorner;
        Ray tempRay;
        for (int i = -1; i <= 1; i += 2){
            for (int j = -1; j <= 1; j += 2) {
                tempCorner = centerP.add(Vright.scale(i * Width / 2)).add(Vup.scale(j * Height / 2));
                cornersList.add(tempCorner);
                if (prePoints == null || !isInList(prePoints, tempCorner)) {
                    tempRay = new Ray(cameraLoc, tempCorner.subtract(cameraLoc));
                    nextCenterPList.add(centerP.add(Vright.scale(i * Width / 4)).add(Vup.scale(j * Height / 4)));
                    colorList.add(TraceRay(tempRay));
                }
            }
        }


        if (nextCenterPList == null || nextCenterPList.size() == 0) {
            return primitives.Color.BLACK;
        }


        boolean isAllEquals = true;
        primitives.Color tempColor = colorList.get(0);
        for (primitives.Color color : colorList) {
            if (!tempColor.isAlmostEquals(color))
                isAllEquals = false;
        }
        if (isAllEquals && colorList.size() > 1)
            return tempColor;


        tempColor = primitives.Color.BLACK;
        for (Point center : nextCenterPList) {
            tempColor = tempColor.add(AdaptiveSuperSamplingRec(center, Width/2,  Height/2,  minWidth,  minHeight ,  cameraLoc, Vright, Vup, cornersList));
        }
        return tempColor.reduce(nextCenterPList.size());


    }

    /**
     * Find a point in the list
     * @param pointsList the list
     * @param point the point that we look for
     * @return
     */
    private boolean isInList(List<Point> pointsList, Point point) {
        for (Point tempPoint : pointsList) {
            if(point.equals(tempPoint))
                return true;
        }
        return false;
    }
}




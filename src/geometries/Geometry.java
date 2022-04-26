package geometries;

import primitives.*;
/**
 * Abstract class for all the geometries shape. <br/>
 * Provides a normal vector for the geometry at certain point, emission color and the material.
 * @author efrat and rivki
 */
public abstract class Geometry extends Intersectable  {
     protected Color emission= Color.BLACK;
     private Material material = new Material();

     /**
      * setter of Emission
      * @param emission
      * @return emission
      */
     public Geometry setEmission(Color emission) {
          this.emission = emission;
          return this;
     }

     /**
      * setter of material
      * @param material
      * @return material
      */
     public Geometry setMaterial(Material material) {
          this.material = material;
          return this;
     }

     /**
      * getter of Emission
      * @return Emission
      */
     public Color getEmission() {return emission; }
     /**
      * getter of material
      * @return material
      */
     public Material getMaterial() {return material; }
     /**
      * getter of material that geting a point
      * @return material
      */
     public abstract Vector getNormal(Point point);
}

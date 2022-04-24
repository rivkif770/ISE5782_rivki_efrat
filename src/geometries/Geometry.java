package geometries;

import primitives.*;

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

     public abstract Vector getNormal(Point point);
}

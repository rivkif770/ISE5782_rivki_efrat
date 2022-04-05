package geometries;

import primitives.*;

public abstract class Geometry extends Intersectable  {
     protected Color emission= Color.BLACK;

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
      * getter of Emission
      * @return Emission
      */
     public Color getEmission() {return emission;}

     public abstract Vector getNormal(Point point);
}

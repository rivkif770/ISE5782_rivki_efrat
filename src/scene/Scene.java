package scene;

import lighting.*;
import geometries.Geometries;
import primitives.Color;

import java.util.List;
import java.util.LinkedList;

public class Scene {
    public String name = null;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries = null;
    public List<LightSource> lighting = new LinkedList<>();

    public Scene(String name) {
        this.name = name;
        geometries = new Geometries();
    }


    // ***************** Setters ********************** //
    // ** all setters implements the Builder Design Pattern **//

    /**
     * set the Background color for the scene
     *
     * @return the scene object
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * set the Ambient Light for the scene
     *
     * @return the scene object
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * set the geometry model - a list of geometries
     *
     * @return the scene object
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * set the lightSource - a list of light sources
     *
     * @return the scene object
     */
    public Scene setLighting(List<LightSource> lighting) {
        this.lighting = lighting;
        return this;
    }
}
package scene;
/**
 * Scene class - combines all the objects needed for the scene:
 * name
 * background
 * ambient lighting
 * the geometry model
 *
 * @authors Tehila Shpayer 325236594 and Sarah Malka Hamou 325266401
 */
import elements.AmbientLight;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;
import java.util.*;
import elements.*;

public class Scene {
    public String name = null;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries = null;

    public Scene(String _name) {
        name = _name;
        geometries = new Geometries();
    }


    // ***************** Setters ********************** //
    // ** all setters implements the Builder Design Pattern **//

    /**
     * set the Background color for the scene
     *
     * @return the scene object
     */
    public Scene setBackground(Color _background) {
        this.background = _background;
        return this;
    }

    /**
     * set the Ambient Light for the scene
     *
     * @return the scene object
     */
    public Scene setAmbientLight(AmbientLight _ambientLight) {
        this.ambientLight = _ambientLight;
        return this;
    }

    /**
     * set the geometry model - a list of geometries
     *
     * @return the scene object
     */
    public Scene setGeometries(Geometries _geometries) {
        this.geometries = _geometries;
        return this;
    }


}
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
import lighting.AmbientLight;
import geometries.Geometries;
import primitives.Color;

public class Scene {
    public String name = null;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries = null;

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


}
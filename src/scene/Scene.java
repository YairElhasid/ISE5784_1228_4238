package scene;


import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;

import java.util.LinkedList;
import java.util.List;

/**
 * the class implements the entire scene (PDS class)
 * @author Sagiv Maoz and Yair Elhasid
 */
public class Scene {

    /**
     * the name of the scene
     */
    public String name;

    /**
     * the background color of the scene
     */
    public primitives.Color background = primitives.Color.BLACK;

    /**
     * the ambient light color of the scene
     */
    public AmbientLight ambientLight = AmbientLight.NONE;

    /**
     * the geometries of the scene
     */
    public Geometries geometries = new Geometries();
    /**
     * source lights of the scene
     */
    public List<LightSource> lights = new LinkedList<>();

    /**
     * name constructor
     * @param name - the name if the scene
     */
    public Scene(String name){
        this.name = name;
    }

    /**
     * setter for the background
     * @param color - new background
     * @return - the scene for concatenation
     */
    public Scene setBackground(primitives.Color color){
        this.background = color;
        return this;
    }

    /**
     * setter for the ambient light
     * @param ambientLight - new ambient light
     * @return - the scene for concatenation
     */
    public Scene setAmbientLight(AmbientLight ambientLight){
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * setter for the geometries
     * @param geometries - new geometries
     * @return - the scene for concatenation
     */
    public Scene setGeometries(Geometries geometries){
        this.geometries = geometries;
        return this;
    }

    /**
     * setter for the lights
     * @param lights - new lights
     * @return - the scene for concatenation
     */
    public Scene setGeometries(List<LightSource> lights){
        this.lights = lights;
        return this;
    }

}

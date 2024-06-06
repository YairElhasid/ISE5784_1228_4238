package renderer;

import primitives.*;

/**
 * class that implements camera
 * @author Sagiv Maoz and Yair Elhasid
 */
public class Camera implements Cloneable{
    private Point location;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double width = 0.0;
    private double height = 0.0;
    private double distance = 0.0;

    private Camera(){};
    
}

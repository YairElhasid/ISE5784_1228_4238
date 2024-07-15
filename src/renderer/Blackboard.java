package renderer;

import geometries.Intersectable;
import primitives.*;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * class of the multi sampling bonus
 * @author Sagiv Maoz and Yair Elhasid
 */
public class Blackboard implements Cloneable {
    /**
     * how random will the place inside the grid will be (jittered)
     */
    private static final int MAX_Random = 4;

    private double pixelHeight = 0;
    private double pixelWidth = 0;
    private int numRays = 0; //the pixel will be numRows*numRows amount of mini pixels
    private Vector Vdown;
    private Vector Vright;
    private Point startingPoint;

    private Blackboard(){}

    /**
     * internal class for builder design pattern
     * @author Sagiv Maoz and Yair Elhasid
     */
    public static class Builder{

        private final Blackboard instance = new Blackboard();

        /**
         * set the pixelHeight of the camera
         * @param pixelHeight - the new point
         * @return - this builder - for concatenation
         */
        public Blackboard.Builder setPixelHeight(double pixelHeight){
            instance.pixelHeight = pixelHeight;
            return this;
        }

        /**
         * set the pixelWidth of the camera
         * @param pixelWidth - the new point
         * @return - this builder - for concatenation
         */
        public Blackboard.Builder setPixelWidth(double pixelWidth){
            instance.pixelWidth = pixelWidth;
            return this;
        }

        /**
         * set the numRows of the camera
         * @param numRows - the new point
         * @return - this builder - for concatenation
         */
        public Blackboard.Builder setNumRows(int numRows){
            instance.numRays = numRows;
            return this;
        }

        /**
         * set the Vdown of the camera
         * @param Vdown - the new point
         * @return - this builder - for concatenation
         */
        public Blackboard.Builder setVdown(Vector Vdown){
            instance.Vdown = Vdown;
            return this;
        }

        /**
         * set the Vright of the camera
         * @param Vright - the new point
         * @return - this builder - for concatenation
         */
        public Blackboard.Builder setVright(Vector Vright){
            instance.Vright = Vright;
            return this;
        }

        /**
         * set the StartingPoint of the camera
         * @param StartingPoint - the new point
         * @return - this builder - for concatenation
         */
        public Blackboard.Builder setStartingPoint(Point StartingPoint){
            instance.startingPoint = StartingPoint;
            return this;
        }


        /**
         * return the final product of the builder and calculate the camera missing arguments
         * @return - the final camera
         */
        public Blackboard build(){
            final String className = "Blackboard", genarlMessege = "Missing camera argument";
            if (instance.pixelHeight == 0) throw new MissingResourceException(genarlMessege,className,"pixelHeight");
            if(instance.pixelWidth == 0) throw new MissingResourceException(genarlMessege,className,"pixelWidth");
            if(instance.Vdown == null) throw new MissingResourceException(genarlMessege,className,"Vdown");
            if(instance.Vright == null) throw new MissingResourceException(genarlMessege,className,"Vright");
            if(instance.numRays == 0) throw new MissingResourceException(genarlMessege,className,"numRays");

            try{
                return (Blackboard) instance.clone(); // Cloneable - get a full copy
            }
            catch(CloneNotSupportedException e){
                throw new RuntimeException("error: ", e);
            }
        }
    }

    /**
     * set the starting point of the pixel (top right)
     * @param StartingPoint the new starting point
     * @return - this Blackboard for concatenation
     */
    public void setStartingPoint(Point StartingPoint){
        this.startingPoint = StartingPoint;
    }

    /**
     * returns an instance of the Builder class - for the builder design pattern
     * @return - instance of the Builder class
     */
    public static Blackboard.Builder getBuilder(){
        return new Blackboard.Builder();
    }

    private LinkedList<Point> constructPoints(){
        LinkedList<Point> points = new LinkedList<>();
        for(double xDirection = 0 ; xDirection < pixelWidth; xDirection+=pixelWidth/numRays){
            for(double yDirection = 0 ; yDirection < pixelHeight; yDirection+=pixelHeight/numRays){
                points.add(startingPoint.add(Vdown.scale(yDirection + ((Math.random() * (MAX_Random + 1)) * (pixelHeight/(numRays * MAX_Random))))).add(Vright.scale(xDirection + ((Math.random() * (MAX_Random + 1)) * (pixelWidth/(numRays * MAX_Random))))));
            }
        }
        return points;
    }

    public LinkedList<Ray> constructRays(Point p0){
        LinkedList<Point> points = constructPoints();
        LinkedList<Ray> rays = new LinkedList<>();
        for(Point point : constructPoints()){
            rays.add(new Ray(p0, point.subtract(p0)));
        }
        return rays;
    }

}

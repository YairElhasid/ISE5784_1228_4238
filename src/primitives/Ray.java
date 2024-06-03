package primitives;


import static primitives.Util.isZero;

/**
 * implement a ray
 * @author Sagiv Maoz and Yair Elhasid
 */
public class Ray {
    final private Point head;
    final private Vector direction;

    /**
     * full constructor
     * @param head - the head point
     * @param direction - the direction vector
     */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize();
    }

    /**
     * head getter
     * @return the head point of the ray
     */
    public Point getHead() {
        return head;
    }
    /**
     * direction getter
     * @return the direction vector of the ray
     */
    public Vector getDirection() {
        return direction;
    }
    /**
     * general point getter (using scalar t)
     * @param t - the scalar
     * @return the point
     */
    public Point getPoint(double t) {
        if(isZero(t)) return head;
        return head.add(direction.scale(t));
    }

    @Override
    public String toString() {
        return "{" + head.toString() + " +t" + direction.toString() + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        return obj instanceof Ray other && head.equals(other.head) && direction.equals(other.direction);
    }
}

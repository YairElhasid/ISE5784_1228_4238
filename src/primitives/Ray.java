package primitives;

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
    @Override
    public String toString() {
        return "point: " + head.toString() + " " + direction.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Ray)) return false;
        return head.equals(((Ray) obj).head) && direction.equals(((Ray) obj).direction);
    }
}

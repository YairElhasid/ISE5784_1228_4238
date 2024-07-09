package renderer;

import geometries.*;
import lighting.AmbientLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;

import static java.awt.Color.*;

        import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
        import renderer.*;
        import scene.Scene;

/** Tests for our final product (without part 1,2)
 * @author Sagiv Maoz and Yair Elhasid */
public class our_final_test {
    /**
     * Scene for the tests
     */
    private final Scene scene = new Scene("Test scene");

    /**
     * Camera builder for the tests with triangles
     */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(1, 1, 0), new Vector(0, 0, 1))
            .setRayTracer(new SimpleRayTracer(scene));

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void OUR() {
        scene.geometries.add(
                new Sphere(new Point(2,2,2), 1).setEmission(new Color(PINK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Sphere(new Point(2,2,2.75), 0.5).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Sphere(new Point(1.6,1.1,2), 0.3).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Sphere(new Point(1.2,1.5,2), 0.3).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Sphere(new Point(1.4,0.9,2), 0.1).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Sphere(new Point(1,1.3,2), 0.1).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Sphere(new Point(1.4,1.4,1.5), 0.2).setEmission(new Color(RED))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100).setKT(0.5)),
                new Triangle(new Point(7,0,0), new Point(0,7,0), new Point(5,5,7)).setEmission(new Color(100, 100, 100))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100).setKR(1)),

                new Polygon(new Point(0,-1.5,-1.5), new Point(0,1.5,-1.5), new Point(0,1.5,1.5), new Point(0,-1.5,1.5)).setEmission(new Color(YELLOW))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100).setKT(0.75))


        );

        scene.lights.add(
                new SpotLight(new Point(-100, -100, 500), new Color(1000, 600, 0), new Vector(-1, -1, -1))
                        .setKL(0.0004).setKQ(0.0000006));

        Camera camera = cameraBuilder.setLocation(new Point(-10, -10, 2)).setVpDistance(300)
                .setVpSize(150, 150)
                .setImageWriter(new ImageWriter("ourtest", 500, 500))
                .build();
        camera.renderImage();
        camera.writeToImage();
    }


}

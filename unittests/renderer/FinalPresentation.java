package renderer;

import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

import static java.awt.Color.*;
import static java.awt.Color.YELLOW;

/**
 * a scene for final presentation
 * @author Yair Elhasid and Sagiv Maoz
 */
public class FinalPresentation {

    /**
     * Scene for the tests
     */
    private final Scene scene = new Scene("Test scene");


    /**
     * Camera builder for the tests with triangles without super sampling
     */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(1, 1, 0), new Vector(0, 0, 1));

    /**
     * Produce a picture of a sphere lighted by a spot light - with super sampling
     */
    @Test
    public void our_with_super() {
        //the walls:
        scene.geometries.add(
                new Plane(new Point(-10000, 0,0), new Point(0, -10000, 0), new Point(-10000, 0, 1)).setEmission(new Color(120, 120, 120))
                        .setMaterial(new Material().setKD(1).setKS(1).setNShininess(1000)),
                new Polygon(new Point(30, 30, 0), new Point(-30, 30, 0), new Point(-30, -30, 0), new Point(30, -30, 0)).setEmission(new Color(100, 100, 100))
                        .setMaterial(new Material().setKD(1).setKS(1).setNShininess(1000)),
                new Polygon(new Point(30, 30, 0), new Point(30, 30, 30), new Point(-30, 30, 30), new Point(-30, 30, 0)).setEmission(new Color(100, 100, 100))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(30, 30, 0), new Point(30, 30, 30), new Point(30, -30, 30), new Point(30, -30, 0)).setEmission(new Color(100, 100, 100))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                //the grid of the stripes
                new Polygon(new Point(30, 30, 0), new Point(30, 30, 0.1), new Point(-30, 30, 0.1), new Point(-30, 30, 0)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(30, 30, 0), new Point(30, 30, 0.1), new Point(30, -30, 0.1), new Point(30, -30, 0)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(29.9, 30, 0), new Point(29.9, 30, 30), new Point(30, 29.9, 30), new Point(30, 29.9, 0)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(18, 30, 0), new Point(18, 30, 30), new Point(18.2, 30, 30), new Point(18.2, 30, 0)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(24, 30, 0), new Point(24, 30, 30), new Point(24.2, 30, 30), new Point(24.2, 30, 0)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(12, 30, 0), new Point(12, 30, 30), new Point(12.2, 30, 30), new Point(12.2, 30, 0)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(6, 30, 0), new Point(6, 30, 30), new Point(6.2, 30, 30), new Point(6.2, 30, 0)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(30, 6, 0), new Point(30, 6, 30), new Point(30,6.2, 30), new Point(30,6.2, 0)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(30, 12, 0), new Point(30, 12, 30), new Point(30,12.2, 30), new Point(30,12.2, 0)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(30, 18, 0), new Point(30, 18, 30), new Point(30,18.2, 30), new Point(30,18.2, 0)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(30, 24, 0), new Point(30, 24, 30), new Point(30,24.2, 30), new Point(30,24.2, 0)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(0, 30, 30), new Point(30, 30, 30), new Point(30,30, 29.8), new Point(0,30, 29.8)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(29.9, 0, 24), new Point(29.9, 29.9, 24), new Point(29.9,29.9, 23.8), new Point(29.9,0, 23.8)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(29.9, 0, 18), new Point(29.9, 29.9, 18), new Point(29.9,29.9, 17.8), new Point(29.9,0, 17.8)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(29.9, 0, 12), new Point(29.9, 29.9, 12), new Point(29.9,29.9, 11.8), new Point(29.9,0, 11.8)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(29.9, 0, 6), new Point(29.9, 29.9, 6), new Point(29.9,29.9, 5.8), new Point(29.9,0, 5.8)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(0, 29.9, 6), new Point(29.9, 29.9, 6), new Point(29.9,29.9, 5.8), new Point(0, 29.9, 5.8)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(0, 29.9, 12), new Point(29.9, 29.9, 12), new Point(29.9,29.9, 11.8), new Point(0, 29.9, 11.8)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(0, 29.9, 18), new Point(29.9, 29.9, 18), new Point(29.9,29.9, 17.8), new Point(0, 29.9, 17.8)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(0, 29.9, 24), new Point(29.9, 29.9, 24), new Point(29.9,29.9, 23.8), new Point(0, 29.9, 23.8)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(24, 29.9, 0.01), new Point(23.6, 29.9, 0.01), new Point(23.6, -30, 0.01), new Point(24, -30, 0.01)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(18, 29.9, 0.01), new Point(17.6, 29.9, 0.01), new Point(17.6, -30, 0.01), new Point(18, -30, 0.01)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(12, 29.9, 0.01), new Point(11.6, 29.9, 0.01), new Point(11.6, -30, 0.01), new Point(12, -30, 0.01)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(6, 29.9, 0.01), new Point(5.6, 29.9, 0.01), new Point(5.6, -30, 0.01), new Point(6, -30, 0.01)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(0, 29.9, 0.01), new Point(-0.4, 29.9, 0.01), new Point(-0.4, -30, 0.01), new Point(0, -30, 0.01)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(-6, 29.9, 0.01), new Point(-6.4, 29.9, 0.01), new Point(-6.4, -30, 0.01), new Point(-6, -30, 0.01)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(-12, 29.9, 0.01), new Point(-12.4, 29.9, 0.01), new Point(-12.4, -30, 0.01), new Point(-12, -30, 0.01)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(29.9, 24, 0.01), new Point(29.9, 23.6, 0.01), new Point(-30, 23.6, 0.01), new Point(-30, 24, 0.01))
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(29.9, 18, 0.01), new Point(29.9, 17.6, 0.01), new Point(-30, 17.6, 0.01), new Point(-30, 18, 0.01))
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(29.9, 12, 0.01), new Point(29.9, 11.6, 0.01), new Point(-30, 11.6, 0.01), new Point(-30, 12, 0.01))
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(29.9, 6, 0.01), new Point(29.9, 5.6, 0.01), new Point(-30, 5.6, 0.01), new Point(-30, 6, 0.01))
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(29.9, 0, 0.01), new Point(29.9, -0.4, 0.01), new Point(-30, -0.4, 0.01), new Point(-30, 0, 0.01))
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(29.9, -6, 0.01), new Point(29.9, -6.4, 0.01), new Point(-30, -6.4, 0.01), new Point(-30, -6, 0.01))
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                new Polygon(new Point(29.9, -12, 0.01), new Point(29.9, -12.4, 0.01), new Point(-30, -12.4, 0.01), new Point(-30, -12, 0.01))
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100)),
                //the shapes
                new Sphere(new Point(9, 23, 2), 2).setEmission(new Color(0,0,0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKR(0.5)),
                new Sphere(new Point(20, 15, 5), 5).setEmission(new Color(0,0,100))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0).setKR(0.01)),

                new Polygon(
                        new Point(14, 14, 0),
                        new Point(16, 14, 0),
                        new Point(16, 16, 0),
                        new Point(14, 16, 0)
                ).setEmission(new Color(10, 10, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(1).setKS(0)),

                new Polygon(
                        new Point(14, 14, 2),
                        new Point(16, 14, 2),
                        new Point(16, 16, 2),
                        new Point(14, 16, 2)
                ).setEmission(new Color(30, 30, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(1).setKS(0)),

                new Polygon(
                        new Point(14, 14, 0),
                        new Point(16, 14, 0),
                        new Point(16, 14, 2),
                        new Point(14, 14, 2)
                ).setEmission(new Color(30, 30, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(1).setKS(0)),

                new Polygon(
                        new Point(14, 16, 0),
                        new Point(16, 16, 0),
                        new Point(16, 16, 2),
                        new Point(14, 16, 2)
                ).setEmission(new Color(30, 30, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(1).setKS(0)),

                new Polygon(
                        new Point(14, 14, 0),
                        new Point(14, 16, 0),
                        new Point(14, 16, 2),
                        new Point(14, 14, 2)
                ).setEmission(new Color(30, 30, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(1).setKS(0)),

                new Polygon(
                        new Point(16, 14, 0),
                        new Point(16, 16, 0),
                        new Point(16, 16, 2),
                        new Point(16, 14, 2)
                ).setEmission(new Color(30, 30, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(1).setKS(0)),

                //THE pyramid
                //first layer:
                new Triangle(new Point(0, 0, 0), new Point(0, 2, 0), new Point(1, 1, 2)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                new Triangle(new Point(0, 2, 0), new Point(0, 4, 0), new Point(1, 3, 2)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                new Triangle(new Point(0, 4, 0), new Point(0, 6, 0), new Point(1, 5, 2)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                new Triangle(new Point(0, 6, 0), new Point(2, 6, 0), new Point(1, 5, 2)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                new Triangle(new Point(2, 6, 0), new Point(4, 6, 0), new Point(3, 5, 2)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                new Triangle(new Point(4, 6, 0), new Point(6, 6, 0), new Point(5, 5, 2)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                new Triangle(new Point(0, 0, 0), new Point(2, 0, 0), new Point(1, 1, 2)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                new Triangle(new Point(2, 0, 0), new Point(4, 0, 0), new Point(3, 1, 2)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                new Triangle(new Point(4, 0, 0), new Point(6, 0, 0), new Point(5, 1, 2)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                new Triangle(new Point(6, 0, 0), new Point(6, 2, 0), new Point(5, 1, 2)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                new Triangle(new Point(6, 2, 0), new Point(6, 4, 0), new Point(5, 3, 2)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                new Triangle(new Point(6, 4, 0), new Point(6, 6, 0), new Point(5, 5, 2)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                //second layer:
                new Triangle(new Point(1, 1, 2), new Point(1, 3, 2), new Point(2, 2, 4)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                new Triangle(new Point(1, 3, 2), new Point(1, 5, 2), new Point(2, 4, 4)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                new Triangle(new Point(1, 5, 2), new Point(3, 5, 2), new Point(2, 4, 4)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                new Triangle(new Point(3, 5, 2), new Point(5, 5, 2), new Point(4, 4, 4)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                new Triangle(new Point(1, 1, 2), new Point(3, 1, 2), new Point(2, 2, 4)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                new Triangle(new Point(3, 1, 2), new Point(5, 1, 2), new Point(4, 2, 4)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                new Triangle(new Point(5, 1, 2), new Point(5, 3, 2), new Point(4, 2, 4)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                new Triangle(new Point(5, 3, 2), new Point(5, 5, 2), new Point(4, 4, 4)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                //third layer:
                new Triangle(new Point(2, 4, 4), new Point(4, 4, 4), new Point(2.7, 3, 6)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                new Triangle(new Point(4, 4, 4), new Point(4, 6, 4), new Point(3, 3.1, 6)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                new Triangle(new Point(4, 2, 4), new Point(4, 4, 4), new Point(3.1, 3, 6)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),
                new Triangle(new Point(4, 4, 4), new Point(6, 4, 4), new Point(3, 2.7, 6)).setEmission(new Color(50, 0, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(1000).setKT(0.5).setKS(0)),

                //the sphere on top of the pyramid:
                new Sphere(new Point(3, 3, 8), 2).setEmission(new Color(200, 103, 0))
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100).setKR(0.5))

        );

        scene.lights.add(
                new PointLight(new Point(-500, -500, 500), new Color(WHITE))
                        .setKL(0.0004).setKQ(0.0000006));
        scene.lights.add(new DirectionalLight(new Color(0, 0, 50), new Vector(0, 0, -1)));
        scene.lights.add(new SpotLight(new Point(-50, -50, 30), new Color(50, 0, 0), new Vector(1, 1, -0.001)));
        scene.lights.add(new SpotLight(new Point(0, -50, 30), new Color(50, 0, 50), new Vector(0, 1, -0.001)));
        scene.lights.add(new SpotLight(new Point(-50, 0, 30), new Color(0, 50, 50), new Vector(1, 0, -0.001)));
        scene.background = new Color(120, 120, 120);

        Camera camera = cameraBuilder.setLocation(new Point(-30, -30, 7)).setVpDistance(300)
                .setVpSize(150, 150)

                .setRayTracer(new SimpleRayTracer(scene))
                .setIsAdaptive(true)
                .setNumRays(33)
                .setImageWriter(new ImageWriter("final_presentation", 500, 500))
                .build();
        camera.renderImage();
        camera.writeToImage();
    }
}
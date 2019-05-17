import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Created by AlanBi on 5/17/18.
 */
public class Planet implements Cloneable, PhysicsObject {
    private Transform transform;
    private double mass;

    private int radius;

    private final Color color;

    private static final double G = 6.67e-11;
    private static final Color[] colors = {Color.WHITE, Color.YELLOW, Color.CYAN, Color.ORANGE, Color.GREEN};


    public Planet(double x, double y, double xSpeed, double ySpeed, double mass) {
        this.transform = new Transform(x, y, xSpeed, ySpeed);
        this.mass = mass;

        this.radius = planetRadius(this.mass);
        this.color = colors[new Random().nextInt(colors.length)];
    }

    public Planet(Planet p) {
        this(p.transform.x, p.transform.y, p.transform.xSpeed, p.transform.ySpeed, p.mass);
    }

    public void update(List<Planet> planets, double timeMultiplier) {
        PhysicsUtility.updatePlanet(this, planets, timeMultiplier);
    }

    public static int planetRadius(double mass) {
        return (int) Math.log(mass) / 5;
    }

    public Transform getTransform() {
        return transform;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Color getColor() {
        return color;
    }

    public double getConstant() {
        return G;
    }
}

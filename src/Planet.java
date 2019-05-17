import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Created by AlanBi on 5/17/18.
 */
public class Planet implements Cloneable {
    public Transform transform;
    public double mass;
    public int radius;

    public Color color;

    public static final double G = 6.67e-11;
    public static final Color[] colors = {Color.WHITE, Color.YELLOW, Color.CYAN, Color.ORANGE, Color.GREEN};


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
        this.transform.x += timeMultiplier * this.transform.xSpeed;
        this.transform.y += timeMultiplier * this.transform.ySpeed;

        for (Planet p : planets) {
            if (this == p) {
                continue;
            }

            double r = distance(this, p);
            double gravity = Planet.G * this.mass * p.mass / (r * r);

            this.transform.xSpeed += timeMultiplier * (p.transform.x - this.transform.x) / r * gravity / this.mass;
            this.transform.ySpeed += timeMultiplier * (p.transform.y - this.transform.y) / r * gravity / this.mass;
        }
    }

    private static double distance(Planet p1, Planet p2) {
        return Math.sqrt((p1.transform.x - p2.transform.x) * (p1.transform.x - p2.transform.x) +
                (p1.transform.y - p2.transform.y) * (p1.transform.y - p2.transform.y)) * 1e9;
    }

    public static int planetRadius(double mass) {
        return (int) Math.log(mass) / 5;
    }
}

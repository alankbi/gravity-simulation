import java.awt.*;
import java.util.List;

/**
 * Created by AlanBi on 5/17/18.
 */
public class Planet implements Cloneable {
    public double x;
    public double y;
    public double xSpeed;
    public double ySpeed;

    public double mass;
    public int radius;

    public static final double G = 6.67e-11;

    public Planet(double x, double y, double xSpeed, double ySpeed, double mass) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed / Simulation.DISTANCE_SCALE;
        this.ySpeed = ySpeed / Simulation.DISTANCE_SCALE;
        this.mass = mass;

        this.radius = planetRadius(this.mass);
    }

    public Planet(Planet p) {
        x = p.x;
        y = p.y;
        xSpeed = p.xSpeed / Simulation.DISTANCE_SCALE;
        ySpeed = p.ySpeed / Simulation.DISTANCE_SCALE;
        mass = p.mass;
        radius = p.radius;
    }

    public void update(List<Planet> planets, double timeMultiplier) {
        x += timeMultiplier * xSpeed;
        y += timeMultiplier * ySpeed;

        for (Planet p : planets) {
            if (this == p) {
                continue;
            }

            double r = distance(this, p);
            double gravity = G * this.mass * p.mass / (r * r);

            xSpeed += timeMultiplier * (p.x - this.x) / r * gravity / this.mass;
            ySpeed += timeMultiplier * (p.y - this.y) / r * gravity / this.mass;
        }
    }

    private double distance(Planet p1, Planet p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y)) * 1e9;
    }

    public static int planetRadius(double mass) {
        return (int) Math.log(mass) / 5;
    }
}

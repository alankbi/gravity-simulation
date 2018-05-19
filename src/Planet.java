import java.util.List;
import javax.swing.*;
import java.awt.*;

/**
 * Created by AlanBi on 5/17/18.
 */
public class Planet extends JPanel {
    private double x;
    private double y;
    private double xSpeed;
    private double ySpeed;

    private double mass;
    private int radius;

    public static double G = 6.67e-11;

    public Planet(double x, double y, double xSpeed, double ySpeed, double mass) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.mass = mass;

        this.radius = (int) Math.sqrt(Math.log(mass));
        System.out.println((int) Math.sqrt(Math.log(mass)));
    }

    public void update(List<Planet> planets) {
        x += xSpeed;
        y += ySpeed;

        for (Planet p : planets) {
            if (this == p) {
                continue;
            }

            double r = distance(this, p);
            double gravity = G * this.mass * p.mass / (r * r);

            xSpeed += (p.x - this.x) / r * gravity / this.mass;
            ySpeed += (p.y - this.y) / r * gravity / this.mass;
        }
    }

    private double distance(Planet p1, Planet p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y)) * 1e6;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillOval((int) x, (int) y, radius, radius);
    }
}

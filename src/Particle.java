import java.awt.Color;
import java.util.List;

public class Particle implements Cloneable {
    public Transform transform;
    public double mass;
    public double charge;
    public int radius;

    public Color color;

    public static final double K = 8.99e9;

    public Particle(double x, double y, double xSpeed, double ySpeed, double mass, double charge) {
        this.transform = new Transform(x, y, xSpeed, ySpeed);
        this.mass = mass;
        this.charge = charge;

        this.radius = particleRadius(this.mass);
        if (charge > 0) {
            this.color = Color.RED;
        } else if (charge < 0) {
            this.color = Color.BLUE;
        } else {
            this.color = Color.GRAY;
        }
    }

    public Particle(Particle p) {
        this(p.transform.x, p.transform.y, p.transform.xSpeed, p.transform.ySpeed, p.mass, p.charge);
    }

    public void update(List<Particle> particles, double timeMultiplier) {
        this.transform.x += timeMultiplier * this.transform.xSpeed;
        this.transform.y += timeMultiplier * this.transform.ySpeed;

        for (Particle p : particles) {
            if (this == p) {
                continue;
            }

            double r = distance(this, p);
            double force = Particle.K * this.charge * p.charge / (r * r);

            this.transform.xSpeed += timeMultiplier * (p.transform.x - this.transform.x) * force / (this.mass * r);
            this.transform.ySpeed += timeMultiplier * (p.transform.y - this.transform.y) * force / (this.mass * r);
        }
    }

    private static double distance(Particle p1, Particle p2) {
        return Math.sqrt((p1.transform.x - p2.transform.x) * (p1.transform.x - p2.transform.x) +
                (p1.transform.y - p2.transform.y) * (p1.transform.y - p2.transform.y)) * 1e-9;
    }

    public static int particleRadius(double mass) {
        return (int) Math.log(mass * 1e50) / 5;
    }
}

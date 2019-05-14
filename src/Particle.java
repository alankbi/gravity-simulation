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
        PhysicsUtility.updateParticle(this, particles, timeMultiplier);
    }

    public static int particleRadius(double mass) {
        return (int) Math.log(mass * 1e50) / 5;
    }
}

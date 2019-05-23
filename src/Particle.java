import java.awt.Color;
import java.util.List;

public class Particle extends PhysicsObject implements Cloneable {
    private Transform transform;
    private double mass;
    private double charge;
    private int radius;

    private final Color color;

    private static final double K = 8.99e9;

    private MovementManager<Particle> movementManager;

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

        movementManager = new MovementManager<>();
    }

    public Particle(Particle p) {
        this(p.transform.x, p.transform.y, p.transform.xSpeed, p.transform.ySpeed, p.mass, p.charge);
    }

    public void update(List<? extends PhysicsObject> particles, double timeMultiplier) {
        movementManager.update(this, (List<Particle>) particles, timeMultiplier);
    }

    public static int particleRadius(double mass) {
        return (int) Math.log(mass * 1e50) / 5;
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

    public double getMagnitude() {
        return charge;
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
        return K;
    }
}

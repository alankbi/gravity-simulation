import java.awt.Color;
import java.util.List;

public abstract class PhysicsObject {
    public abstract void update(List<? extends PhysicsObject> physicsObjects, double timeMultiplier);

    public abstract Transform getTransform();

    public abstract double getMass();

    public abstract void setMass(double mass);

    // Mass for Planet, charge for Particle
    public abstract double getMagnitude();

    public abstract int getRadius();

    public abstract void setRadius(int radius);

    public abstract Color getColor();

    public abstract double getConstant();
}

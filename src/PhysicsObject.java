import java.awt.Color;

public abstract class PhysicsObject {
    public abstract Transform getTransform();

    public abstract double getMass();

    public abstract void setMass(double mass);

    public abstract int getRadius();

    public abstract void setRadius(int radius);

    public abstract Color getColor();

    public abstract double getConstant();
}

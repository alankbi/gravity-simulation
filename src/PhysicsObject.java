import java.awt.Color;

public interface PhysicsObject {
    Transform getTransform();

    double getMass();

    void setMass(double mass);

    int getRadius();

    void setRadius(int radius);

    Color getColor();

    double getConstant();
}

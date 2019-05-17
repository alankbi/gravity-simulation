import java.util.List;

public class MovementManager<T extends PhysicsObject> {

    public void update(T p, List<T> physicsObjects, double timeMultiplier) {
        Transform transform = p.getTransform();
        transform.x += timeMultiplier * transform.xSpeed;
        transform.y += timeMultiplier * transform.ySpeed;

        for (T p1 : physicsObjects) {
            if (p == p1) {
                continue;
            }

            double r = distance(p, p1);
            double force = p.getConstant() * p.getMagnitude() * p1.getMagnitude() / (r * r);

            transform.xSpeed += timeMultiplier * (p1.getTransform().x - transform.x) / r * force / p.getMass();
            transform.ySpeed += timeMultiplier * (p1.getTransform().y - transform.y) / r * force / p.getMass();
        }
    }

    private double distance(T p1, T p2) {
        Transform t1 = p1.getTransform();
        Transform t2 = p2.getTransform();

        return Math.sqrt((t1.x - t2.x) * (t1.x - t2.x) + (t1.y - t2.y) * (t1.y - t2.y)) * 1e9;
    }
}

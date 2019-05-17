import java.util.List;

public class MovementManager<T extends PhysicsObject> {

    public void updatePlanet(T p, List<T> planets, double timeMultiplier) {
        Transform transform = p.getTransform();
        transform.x += timeMultiplier * transform.xSpeed;
        transform.y += timeMultiplier * transform.ySpeed;

        for (T p1 : planets) {
            if (p == p1) {
                continue;
            }

            double r = distance(p, p1);
            double gravity = p.getConstant() * p.getMass() * p1.getMass() / (r * r);

            transform.xSpeed += timeMultiplier * (p1.getTransform().x - transform.x) / r * gravity / p.getMass();
            transform.ySpeed += timeMultiplier * (p1.getTransform().y - transform.y) / r * gravity / p.getMass();
        }
    }

    public void updateParticle(T p, List<T> particles, double timeMultiplier) {

    }

    // TODO: move updatePlanet code here, have both of them call this
    private void update() {

    }

    private double distance(T p1, T p2) {
        Transform t1 = p1.getTransform();
        Transform t2 = p2.getTransform();

        return Math.sqrt((t1.x - t2.x) * (t1.x - t2.x) + (t1.y - t2.y) * (t1.y - t2.y)) * 1e9;
    }
}

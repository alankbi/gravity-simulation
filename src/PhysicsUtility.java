import java.util.List;

public class PhysicsUtility {

    public static void updatePlanet(Planet p, List<Planet> planets, double timeMultiplier) {
        Transform transform = p.getTransform();
        transform.x += timeMultiplier * transform.xSpeed;
        transform.y += timeMultiplier * transform.ySpeed;

        for (Planet p1 : planets) {
            if (p == p1) {
                continue;
            }

            double r = distance(p, p1);
            double gravity = p.getConstant() * p.getMass() * p1.getMass() / (r * r);

            transform.xSpeed += timeMultiplier * (p1.getTransform().x - transform.x) / r * gravity / p.getMass();
            transform.ySpeed += timeMultiplier * (p1.getTransform().y - transform.y) / r * gravity / p.getMass();
        }
    }

    public static void updateParticle(Particle p, List<Particle> particles, double timeMultiplier) {

    }

    // TODO: move updatePlanet code here, have both of them call this
    private static void update() {

    }

    private static double distance(Planet p1, Planet p2) {
        Transform t1 = p1.getTransform();
        Transform t2 = p2.getTransform();

        return Math.sqrt((t1.x - t2.x) * (t1.x - t2.x) + (t1.y - t2.y) * (t1.y - t2.y)) * 1e9;
    }
}

import java.util.List;

public class PhysicsUtility {

    public static void updatePlanet(Planet p, List<Planet> planets, double timeMultiplier) {
        p.transform.x += timeMultiplier * p.transform.xSpeed;
        p.transform.y += timeMultiplier * p.transform.ySpeed;

        for (Planet p1 : planets) {
            if (p == p1) {
                continue;
            }

            double r = distance(p, p1, 1e9);
            double gravity = Planet.G * p.mass * p1.mass / (r * r);

            p.transform.xSpeed += timeMultiplier * (p1.transform.x - p.transform.x) / r * gravity / p.mass;
            p.transform.ySpeed += timeMultiplier * (p1.transform.y - p.transform.y) / r * gravity / p.mass;
        }
    }

    public static void updateParticle(Particle p, List<Particle> particles, double timeMultiplier) {
        
    }

    // TODO: move updatePlanet code here, have both of them call this
    private static void update() {

    }

    private static double distance(Planet p1, Planet p2, double exponent) {
        return Math.sqrt((p1.transform.x - p2.transform.x) * (p1.transform.x - p2.transform.x) +
                (p1.transform.y - p2.transform.y) * (p1.transform.y - p2.transform.y)) * exponent;
    }
}

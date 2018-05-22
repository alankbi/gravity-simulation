import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

/**
 * Created by AlanBi on 5/21/18.
 */
public class Simulation extends JPanel {

    private List<Planet> planets;

    private double timeMultiplier;

    public static final double DISTANCE_SCALE = 1e9;

    public Simulation() {
        planets = new ArrayList<>();
        timeMultiplier = 1e4;
    }

    public void addPlanet(Planet p) {
        planets.add(p);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);

        for (Planet p : planets) {
            p.update(planets, timeMultiplier);
            g.fillOval((int) p.x, (int) p.y, p.radius, p.radius);
        }
    }
}

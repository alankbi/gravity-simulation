import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlanBi on 5/21/18.
 */
public class Simulation extends JPanel implements ActionListener {

    private List<Planet> planets;
    private int X;
    private int Y;
    private Timer timer;

    private Panel panel;

    public static double timeMultiplier = 1e4;
    public static final double DISTANCE_SCALE = 1e9;

    public Simulation(Panel p, int X, int Y) {
        panel = p;
        this.X = X;
        this.Y = Y;

        planets = new ArrayList<>();
        //timeMultiplier = 1e4;
        timer = new Timer(5, this);
        timer.start();
    }

    public void addPlanet(Planet p) {
        planets.add(p);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (panel.reset) {
            planets.clear();
            panel.reset = false;
        }

        g.setColor(new Color(20, 20, 20));
        g.fillRect(0, 0, X, Y);

        for (int i = 0; i < planets.size(); i++) {
            Planet p = planets.get(i);

            if (p.x > 2000 || p.x < 0 || p.y > 2000 || p.y < 0) {
                planets.remove(i--);
            } else {
                p.update(planets, timeMultiplier);
                g.setColor(p.color);
                g.fillOval((int) p.x, (int) p.y, p.radius, p.radius);
            }
        }

        if (panel.showPlanet) {
            g.setColor(Color.GRAY);
            Planet p = panel.newPlanet;
            g.fillOval((int) p.x, (int) p.y, p.radius, p.radius);
        }
        if (panel.addPlanet) {
            Planet tempPlanet = new Planet(panel.newPlanet);
            addPlanet(tempPlanet);
            panel.addPlanet = false;
        }

    }

    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == timer) {
            repaint();
        }
    }
}

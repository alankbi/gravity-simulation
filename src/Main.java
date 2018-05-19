import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

/**
 * Created by AlanBi on 5/17/18.
 */
public class Main extends JFrame {

    private List<Planet> planets;

    public Main() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(new BorderLayout());
        //getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        planets = new ArrayList<>();
    }

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.setVisible(true);

        main.addPlanet(new Planet(300, 100, 1.2, 0, 6e24));
        main.addPlanet(new Planet(300, 200, 2, 0, 2e30));
        main.addPlanet(new Planet(500, 300, 2, 0, 2e60));

        while (true) {
            Thread.sleep(5);

            List<Planet> planets = main.getPlanets();
            for (Planet p : planets) {
                p.update(planets);
            }
            main.repaint();
            main.revalidate();
        }
    }

    public void addPlanet(Planet p) {
        add(p);//, BorderLayout.PAGE_START);
        planets.add(p);
    }

    public List<Planet> getPlanets() {
        return planets;
    }

}








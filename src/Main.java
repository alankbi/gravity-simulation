import javax.swing.*;

/**
 * Created by AlanBi on 5/17/18.
 */
public class Main extends JFrame {

    private Simulation sim;

    public static final int X = 800;
    public static final int Y = 600;

    public Main() {
        setSize(X, Y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));

        sim = new Simulation();
        add(sim);
        add(new Panel(X / 4, Y));
    }

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.setVisible(true);

        main.addPlanet(new Planet(300, 50, 3e4, 0, 6e24));
        main.addPlanet(new Planet(300, 200, 0, 0, 2e30));

        while (true) {
            Thread.sleep(5);
            main.repaint();
            main.revalidate();
        }
    }

    public void addPlanet(Planet p) {
        sim.addPlanet(p);
    }
}








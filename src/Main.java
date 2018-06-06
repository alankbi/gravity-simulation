import javax.swing.*;

/**
 * Created by AlanBi on 5/17/18.
 */
public class Main extends JFrame {

    private Simulation sim;
    private Panel panel;

    public static final int DEFAULT_X = 800;
    public static final int DEFAULT_Y = 600;

    public Main() {
        this(DEFAULT_X, DEFAULT_Y);
    }

    public Main(int X, int Y) {
        X = Math.max(X, 700);
        Y = Math.max(Y, 500);

        setSize(X, Y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));

        panel = new Panel(X / 4, Y, X, Y);

        sim = new Simulation(panel, 3 * X, Y);
        add(sim);

        add(panel);
    }

    public static void main(String[] args) throws InterruptedException {
        Main main;
        if (args.length >= 2) {
            main = new Main(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        } else {
            main = new Main();
        }
        main.setVisible(true);

        main.addPlanet(new Planet(300, 50, 3e4, 0, 6e24));
        main.addPlanet(new Planet(300, 200, 0, 0, 2e30));
    }

    public void addPlanet(Planet p) {
        sim.addPlanet(p);
    }
}








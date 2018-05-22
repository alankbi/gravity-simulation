import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by AlanBi on 5/21/18.
 */
public class Panel extends JPanel {

    private int X;
    private int Y;

    public Panel(int X, int Y) {
        this.X = X;
        this.Y = Y;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(X, Y));

        JPanel planetsPanel = new JPanel();
        planetsPanel.setLayout(new BoxLayout(planetsPanel, BoxLayout.Y_AXIS));
        planetsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        planetsPanel.setPreferredSize(new Dimension(X, Y / 2));

        JLabel label = new JLabel("Add New Planet");
        label.setPreferredSize(new Dimension(X, Y / 10));
        planetsPanel.add(label);

        planetsPanel.add(makePositionSlider("X", Main.X - X));
        planetsPanel.add(makePositionSlider("Y", Main.Y));

        //planetsPanel.add(makeSpeedSlider("X"));
        //planetsPanel.add(makeSpeedSlider("Y"));

        label = new JLabel("X Speed");
        label.setPreferredSize(new Dimension(X, Y / 15));
        planetsPanel.add(label);

        label = new JLabel("Y Speed");
        label.setPreferredSize(new Dimension(X, Y / 15));
        planetsPanel.add(label);

        add(planetsPanel);
    }

    public JPanel makePositionSlider(String axis, int maxValue) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMaximumSize(new Dimension(X, Y / 5));

        JLabel label = new JLabel(axis + " Position");
        label.setPreferredSize(new Dimension(X, Y / 15));
        panel.add(label);

        JSlider slider = new JSlider(JSlider.HORIZONTAL,0, maxValue, maxValue / 2);
        slider.setPreferredSize(new Dimension(X, 2 * Y / 15));
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                int value = slider.getValue();
                System.out.println(value);
            }
        });

        slider.setMajorTickSpacing(maxValue / 4);
        slider.setMinorTickSpacing(2);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        panel.add(slider);

        return panel;
    }

    public JPanel makeSpeedSlider(String axis) {
        return null;
    }
}

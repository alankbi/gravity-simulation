import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by AlanBi on 5/21/18.
 */
public class Panel extends JPanel {

    private int X;
    private int Y;

    public Planet newPlanet;
    public boolean addPlanet;
    public boolean showPlanet;
    public boolean reset;

    private String xSpeed;
    private String ySpeed;
    private String mass;

    public Panel(int X, int Y, int sim_X, int sim_Y) {
        this.X = X;
        this.Y = Y;

        newPlanet = new Planet(0, 0, 0, 0, 0);
        addPlanet = false;
        showPlanet = true;
        reset = false;

        xSpeed = "0e0";
        ySpeed = "0e0";
        mass = "0e0";

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setMaximumSize(new Dimension(X, Y));

        JPanel planetsPanel = new JPanel();
        planetsPanel.setLayout(new BoxLayout(planetsPanel, BoxLayout.Y_AXIS));
        planetsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        planetsPanel.setPreferredSize(new Dimension(X, 2 * Y / 3));

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(X, Y / 5));

        JLabel label = new JLabel("Add New Planet");
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);

        JCheckBox checkBox = new JCheckBox("Show Planet");
        checkBox.setSelected(true);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                showPlanet = checkBox.isSelected();
            }
        });

        panel.add(checkBox);
        planetsPanel.add(panel);

        planetsPanel.add(makePositionSlider("X", sim_X - X));
        planetsPanel.add(makePositionSlider("Y", sim_Y));

        planetsPanel.add(makeSpinner("X", false));
        planetsPanel.add(makeSpinner("Y", false));

        planetsPanel.add(makeSpinner("", true));

        JButton addPlanetButton = new JButton("Add Planet");
        addPlanetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlanet = true;
            }
        });
        planetsPanel.add(addPlanetButton);

        planetsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(planetsPanel);


        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        optionsPanel.setPreferredSize(new Dimension(X, 2 * Y / 9));

        JLabel scale = new JLabel(" 1 pixel is 10^9 meters");
        scale.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel = new JPanel();
        panel.add(scale);
        optionsPanel.add(panel);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setMaximumSize(new Dimension(X, Y / 16));

        label = new JLabel(" Time");
        label.setPreferredSize(new Dimension(X / 4, Y / 16));
        panel.add(label);

        JSlider slider = new JSlider(JSlider.HORIZONTAL,0, 8, 1);
        slider.setMinimumSize(new Dimension(3 * X / 4, Y / 16));
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                int value = slider.getValue();
                Simulation.timeMultiplier = value * 1e4;
            }
        });

        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        panel.add(slider);

        optionsPanel.add(panel);

        JButton resetButton = new JButton("Reset Simulation");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset = true;
            }
        });
        optionsPanel.add(resetButton);

        optionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(optionsPanel);


        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setPreferredSize(new Dimension(X, Y / 9));

        label = new JLabel(" Physics Simulation");
        label.setPreferredSize(new Dimension(X, Y / 18));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(label);
        label = new JLabel(" Created by Alan Bi");
        label.setPreferredSize(new Dimension(X, Y / 18));
        infoPanel.add(label);

        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(infoPanel);
    }

    public JPanel makePositionSlider(String axis, int maxValue) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setMaximumSize(new Dimension(X, Y / 5));

        JLabel label = new JLabel(" " + axis + " Pos.");
        label.setPreferredSize(new Dimension(X / 4, Y / 5));
        panel.add(label);

        JSlider slider = new JSlider(JSlider.HORIZONTAL,0, maxValue, 0);
        slider.setPreferredSize(new Dimension(3 * X / 4, Y / 5));
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                int value = slider.getValue();
                if (axis.equals("X")) {
                    newPlanet.x = value;
                } else {
                    newPlanet.y = value;
                }
            }
        });

        slider.setMajorTickSpacing(maxValue / 2);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        panel.add(slider);

        return panel;
    }

    public JPanel makeSpinner(String axis, boolean isMassSpinner) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setMaximumSize(new Dimension(X, Y / 5));

        JLabel label = new JLabel(" " + axis + " Vel.");
        if (isMassSpinner) {
            label = new JLabel(" Mass");
        }
        label.setPreferredSize(new Dimension(X / 4, Y / 5));
        panel.add(label);

        int minVal = -10;
        if (isMassSpinner) minVal = 0;

        JSpinner spinner1 = new JSpinner(new SpinnerNumberModel(0, minVal, 10, 1));
        spinner1.setMaximumSize(new Dimension(X /4, Y / 5));
        spinner1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = (int) spinner1.getValue();

                if (isMassSpinner) {
                    mass = value + "e" + mass.substring(mass.indexOf("e") + 1);
                    newPlanet.mass = Double.parseDouble(mass);
                    newPlanet.radius = Planet.planetRadius(newPlanet.mass);
                } else {
                    if (axis.equals("X")) {
                        xSpeed = value + "e" + xSpeed.substring(xSpeed.indexOf("e") + 1);
                        newPlanet.xSpeed = Double.parseDouble(xSpeed);
                    } else {
                        ySpeed = value + "e" + ySpeed.substring(ySpeed.indexOf("e") + 1);
                        newPlanet.ySpeed = Double.parseDouble(ySpeed);
                    }
                }
            }
        });
        panel.add(spinner1);

        label = new JLabel(" x 10^");
        label.setPreferredSize(new Dimension(X / 4, Y / 5));
        panel.add(label);

        JSpinner spinner2 = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
        spinner2.setMaximumSize(new Dimension(X / 4, Y / 5));
        spinner2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = (int) spinner2.getValue();

                if (isMassSpinner) {
                    mass = mass.substring(0, mass.indexOf("e") + 1) + value;
                    newPlanet.mass = Double.parseDouble(mass);
                    newPlanet.radius = Planet.planetRadius(newPlanet.mass);
                } else {
                    if (axis.equals("X")) {
                        xSpeed = xSpeed.substring(0, xSpeed.indexOf("e") + 1) + value;
                        newPlanet.xSpeed = Double.parseDouble(xSpeed);
                    } else {
                        ySpeed = ySpeed.substring(0, ySpeed.indexOf("e") + 1) + value;
                        newPlanet.ySpeed = Double.parseDouble(ySpeed);
                    }
                }
            }
        });
        panel.add(spinner2);

        return panel;
    }
}

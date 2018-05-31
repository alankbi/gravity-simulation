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

    private String xSpeed;
    private String ySpeed;
    private String mass;

    public Panel(int X, int Y) {
        this.X = X;
        this.Y = Y;

        newPlanet = new Planet(0, 0, 0, 0, 0);
        addPlanet = false;
        showPlanet = true;

        xSpeed = "0e0";
        ySpeed = "0e0";
        mass = "0e0";

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(X, Y));

        JPanel planetsPanel = new JPanel();
        planetsPanel.setLayout(new BoxLayout(planetsPanel, BoxLayout.Y_AXIS));
        planetsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        planetsPanel.setMaximumSize(new Dimension(X, 2 * Y / 3));

        JLabel label = new JLabel("Add New Planet");
        label.setPreferredSize(new Dimension(X, Y / 10));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        planetsPanel.add(label);

        JCheckBox checkBox = new JCheckBox("Show Planet");
        checkBox.setSelected(true);
        checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                showPlanet = checkBox.isSelected();
            }
        });
        planetsPanel.add(checkBox);

        planetsPanel.add(makePositionSlider("X", Main.X - X));
        planetsPanel.add(makePositionSlider("Y", Main.Y));

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

        add(planetsPanel);


        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        optionsPanel.setMaximumSize(new Dimension(X, Y / 3));

        add(optionsPanel);
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

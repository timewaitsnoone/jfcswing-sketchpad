package app.component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.swing.border.*;
import javax.swing.event.*;

import app.main.AppConfig;
import app.util.*;

/**
 * This class implements a panel that allows
 * the user to change and update the settings
 * of the application.
 */
public class SettingsPanel extends JPanel {

    private Map<String, AbstractButton> buttons = new HashMap<String, AbstractButton>();

    private void configButtons() {
        buttons.put("ZOOM-IN",  UIToolbox.createButton(UIToolbox.BUTTON,       "ZOOM-IN",  "ZOOM-IN",  "Zoom In", 12.0f, 0));
        buttons.put("ZOOM-OUT", UIToolbox.createButton(UIToolbox.BUTTON,       "ZOOM-OUT", "ZOOM-OUT", "Zoom Out", 12.0f, 0));
        buttons.put("LINKED",   UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "LINKED",   "LINK",     "", 12.0f, 0));
    }

    /**
     * Show the label beside the thumb of the
     * given slider.
     *
     * @param slider        to show the label for
     * @param values        the values of the ticks
     */
    private static void showLabel(JSlider slider, Map<Integer, Integer> values) {
        Hashtable<Integer, JLabel> label = new Hashtable<Integer, JLabel>();
        JLabel jLabel = new JLabel(values.get(slider.getValue()).toString());
            jLabel.setPreferredSize(new Dimension(30, 20));
            label.put(slider.getValue(), jLabel);
        slider.setLabelTable(label);
        slider.setPaintLabels(true);
    }

    private final JSlider zoomSlider;
    private final JTextField widthTextField;
    private final JTextField heightTextField;
    private final JCheckBox scaled;
    private final AbstractButton linked;
    private final JButton saveSizeButton;

    public SettingsPanel() {
        configButtons();
        // Zoom Panel
        JPanel zoomPanel = new JPanel();
            AbstractButton zoomIn = (AbstractButton)buttons.get("ZOOM-IN");
                AppConfig.zoomIn = zoomIn;
                zoomIn.setBorder(null);
            AbstractButton zoomOut = (AbstractButton)buttons.get("ZOOM-OUT");
                AppConfig.zoomOut = zoomOut;
                zoomOut.setBorder(null);
            this.zoomSlider = new JSlider(JSlider.VERTICAL, 0, 6, 3);
                AppConfig.zoomSlider = zoomSlider;
                final Map<Integer, Integer> zoomValues = new HashMap<Integer, Integer>();
                    zoomValues.put(0, 25);
                    zoomValues.put(1, 50);
                    zoomValues.put(2, 75);
                    zoomValues.put(3, 100);
                    zoomValues.put(4, 200);
                    zoomValues.put(5, 400);
                    zoomValues.put(6, 800);
                zoomSlider.setMajorTickSpacing(1);
                zoomSlider.setPaintTicks(true);
                zoomSlider.setSnapToTicks(true);
                zoomSlider.setPreferredSize(new Dimension(60, 100));
                zoomSlider.addChangeListener(new ChangeListener() {
                    @Override public void stateChanged(ChangeEvent arg0) {
                        showLabel(zoomSlider, zoomValues);
                        AppConfig.zoom = (double)zoomValues.get(zoomSlider.getValue())/100;
                        AppConfig.drawingArea.zoom();
                    }
                });
                showLabel(zoomSlider, zoomValues);
                zoomIn.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        if (zoomSlider.getValue() < zoomSlider.getMaximum()) {
                            zoomSlider.setValue(zoomSlider.getValue() + 1);
                        }
                    }
                });
                zoomOut.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        if (zoomSlider.getValue() > zoomSlider.getMinimum()) {
                            zoomSlider.setValue(zoomSlider.getValue() - 1);
                        }
                    }
                });
            GroupLayout zoomLayout = new GroupLayout(zoomPanel);
                zoomLayout.setAutoCreateGaps(true);
                zoomLayout.setAutoCreateContainerGaps(true);
                zoomPanel.setLayout(zoomLayout);
                zoomLayout.setHorizontalGroup(zoomLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(zoomIn, 30, 30, 30)
                    .addComponent(zoomSlider)
                    .addComponent(zoomOut, 30, 30, 30));
                zoomLayout.setVerticalGroup(zoomLayout.createSequentialGroup()
                    .addComponent(zoomIn, 30, 30, 30)
                    .addComponent(zoomSlider)
                    .addComponent(zoomOut, 30, 30, 30));
            zoomPanel.setBorder(new TitledBorder(new EtchedBorder(), "Zoom",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                MyFont.REGULAR_FONT.deriveFont(10.f),
                Color.LIGHT_GRAY));
        // Document Size Panel
        JPanel sizePanel = new JPanel();
            JLabel widthLabel  = new JLabel("Width:");
                widthLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            JLabel heightLabel = new JLabel("Height:");
                heightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            this.widthTextField  = new JTextField(5);
                widthTextField.setHorizontalAlignment(JTextField.RIGHT);
            this.heightTextField = new JTextField(5);
                heightTextField.setHorizontalAlignment(JTextField.RIGHT);
            JLabel widthUnits  = new JLabel("px");
            JLabel heightUnits = new JLabel("px");
            this.linked = buttons.get("LINKED");
                linked.setBorder(null);
            this.scaled = new JCheckBox("Scale Drawings");
            this.saveSizeButton = new JButton("Apply");
            GroupLayout sizeLayout = new GroupLayout(sizePanel);
                sizeLayout.setAutoCreateGaps(true);
                sizeLayout.setAutoCreateContainerGaps(true);
                sizePanel.setLayout(sizeLayout);
                sizeLayout.setHorizontalGroup(sizeLayout.createParallelGroup()
                    .addGap(25)
                    .addGroup(sizeLayout.createSequentialGroup()
                        .addGroup(sizeLayout.createParallelGroup(Alignment.TRAILING)
                            .addComponent(widthLabel,  50, 50, 50)
                            .addComponent(heightLabel, 50, 50, 50))
                        .addGroup(sizeLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(widthTextField,  80, 80, 80)
                            .addComponent(heightTextField, 80, 80, 80))
                        .addGroup(sizeLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(widthUnits)
                            .addComponent(heightUnits))
                        .addComponent(linked, 30, 30, 30))
                    .addComponent(scaled)
                    .addGap(25)
                    .addComponent(saveSizeButton, Alignment.TRAILING));
                sizeLayout.setVerticalGroup(sizeLayout.createSequentialGroup()
                    .addGap(25)
                    .addGroup(sizeLayout.createParallelGroup(Alignment.CENTER)
                        .addGroup(sizeLayout.createSequentialGroup()
                            .addGroup(sizeLayout.createParallelGroup(Alignment.CENTER)
                                .addComponent(widthLabel)
                                .addComponent(widthTextField)
                                .addComponent(widthUnits))
                            .addGroup(sizeLayout.createParallelGroup(Alignment.CENTER)
                                .addComponent(heightLabel)
                                .addComponent(heightTextField)
                                .addComponent(heightUnits)))
                        .addComponent(linked, 40, 40, 40))
                    .addComponent(scaled)
                    .addGap(25)
                    .addComponent(saveSizeButton));
            sizePanel.setBorder(new TitledBorder(new EtchedBorder(), "Document Size",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                MyFont.REGULAR_FONT.deriveFont(10.f),
                Color.LIGHT_GRAY));
        // Put it all together
        setLayout(new BorderLayout());
        JPanel outer = new JPanel();
            GroupLayout layout = new GroupLayout(outer);
                outer.setLayout(layout);
                layout.setAutoCreateContainerGaps(true);
                layout.setHorizontalGroup(layout.createSequentialGroup()
                    .addComponent(zoomPanel)
                    /*.addComponent(sizePanel)*/);
                layout.setVerticalGroup(layout.createParallelGroup()
                    .addComponent(zoomPanel)
                    /*.addComponent(sizePanel)*/);
        add(outer, BorderLayout.CENTER);

        this.widthTextField.setText("" + AppConfig.size.width);
        this.heightTextField.setText("" + AppConfig.size.height);
        this.linked.setSelected(true);
        this.saveSizeButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {

            }
        });

    }

} // SettingsPanel

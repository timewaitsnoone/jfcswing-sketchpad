package app.component;

import app.drawing.ShapeDrawing;
import app.main.AppConfig;
import app.util.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class constructs the main toolbar
 * for the application window.
 */
public class MainToolbar extends JToolBar {

    private Map<String, AbstractButton> buttons = new HashMap<String, AbstractButton>();

    private void configButtons() {
        buttons.put("NEW"      , UIToolbox.createButton(UIToolbox.BUTTON      , "NEW"      , "NEW"    , "New Drawing... (Ctrl+N)"));
        buttons.put("OPEN"     , UIToolbox.createButton(UIToolbox.BUTTON      , "OPEN"     , "OPEN"   , "Open Drawing... (Ctrl+O)"));
        buttons.put("SAVE"     , UIToolbox.createButton(UIToolbox.BUTTON      , "SAVE"     , "SAVE"   , "Save Drawing... (Ctrl+S)"));
        buttons.put("PRINT"    , UIToolbox.createButton(UIToolbox.BUTTON      , "PRINT"    , "PRINT"  , "Print Drawing... (Ctrl+P)"));
        buttons.put("UNDO"     , UIToolbox.createButton(UIToolbox.BUTTON      , "UNDO"     , "UNDO"   , "Undo Change (Ctrl+Z)"));
        buttons.put("REDO"     , UIToolbox.createButton(UIToolbox.BUTTON      , "REDO"     , "REDO"   , "Redo Change (Ctrl+Y)"));
        buttons.put("DELETE"   , UIToolbox.createButton(UIToolbox.BUTTON      , "DELETE"   , "ERASER" , "Delete Drawing"));
        buttons.put("SELECT"   , UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "SELECT"   , "SELECT" , "Select Drawing"));
        buttons.put("DRAW"     , UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "DRAW"     , "PENCIL" , "Draw..."));
        buttons.put("STYLE"    , UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "STYLE"    , "PAINT"  , "Style..."));
        buttons.put("LAYERS"   , UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "LAYERS"   , "LAYERS" , "Layers"));
        buttons.put("LIBRARY"  , UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "LIBRARY"  , "LIBRARY", "Show Gallery"));
        buttons.put("SETTINGS" , UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "SETTINGS" , "GEAR"   , "Show Settings"));
        buttons.put("FILL"     , UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "FILL"     , "BUCKET" , "Fill Color"));
        buttons.put("LINE"     , UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "LINE"     , "PEN"    , "Line Color"));
        buttons.put("GRID"     , UIToolbox.createButton(UIToolbox.TOGGLEBUTTON, "GRID"     , "RULER"  , "Show/Hide Grid"));
    }

    public MainToolbar() {
        setFloatable(false);
        setLayout(new BorderLayout());
        configButtons();
        JToolBar leftGroup = new JToolBar();
            leftGroup.add(buttons.get("NEW"));
                buttons.get("NEW").addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        FileOperations.newDrawing();
                    }
                });
            leftGroup.add(buttons.get("OPEN"));
                buttons.get("OPEN").addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        FileOperations.openDrawing();
                    }
                });
            leftGroup.add(buttons.get("SAVE"));
                buttons.get("SAVE").addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        FileOperations.saveDrawing(false, false);
                    }
                });
            leftGroup.addSeparator();
            ButtonGroup modeGroup = new ButtonGroup();
                modeGroup.add((AbstractButton)leftGroup.add(buttons.get("SELECT")));
                modeGroup.add((AbstractButton)leftGroup.add(buttons.get("DRAW")));
                buttons.get("SELECT").setSelected(true);
                buttons.get("SELECT").addItemListener(new ItemListener() {
                    @Override public void itemStateChanged(ItemEvent e) {
                        if (buttons.get("SELECT").isSelected()) {
                            AppConfig.mode = AppConfig.Mode.SELECT;
                        }
                    }
                }); // addItemListener
                buttons.get("DRAW").addItemListener(new ItemListener() {
                    private final String original = buttons.get("DRAW").getText();
                    @Override public void itemStateChanged(ItemEvent e) {
                        if (!buttons.get("DRAW").isSelected()) {
                            buttons.get("DRAW").setText(original);
                        } // not selected
                    }
                }); // addItemListener
                leftGroup.add(buttons.get("DELETE"));
                    buttons.get("DELETE").addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            if (AppConfig.mode == AppConfig.Mode.SELECT && AppConfig.selected != null) {
                                AppConfig.drawings.remove(AppConfig.selected);
                                AppConfig.selected = null;
                            }
                            AppConfig.drawingArea.repaint();
                        }
                    });
            leftGroup.addSeparator();
            leftGroup.add(buttons.get("GRID"));
                buttons.get("GRID").addItemListener(new ItemListener() {
                    @Override public void itemStateChanged(ItemEvent e) {
                        AppConfig.isGrid = buttons.get("GRID").isSelected();
                        AppConfig.drawingArea.repaint();
                    }
                }); // addItemListener
            leftGroup.addSeparator();
            leftGroup.setFloatable(false);
            leftGroup.setOpaque(false);

        add(leftGroup, BorderLayout.WEST);

        AppConfig.fillButton = buttons.get("FILL");
        AppConfig.lineButton = buttons.get("LINE");

        JToolBar rightGroup = new JToolBar();
            rightGroup.addSeparator();
            rightGroup.add(AppConfig.fillButton);
            rightGroup.add(AppConfig.lineButton);
            rightGroup.addSeparator();
            rightGroup.add(buttons.get("SETTINGS"));
            rightGroup.setFloatable(false);
            rightGroup.setOpaque(false);

        add(rightGroup, BorderLayout.EAST);
        // popups
        JToolBar drawingModePane   = new DrawingSelectionPane(buttons.get("DRAW"));
        JPanel   settingPane       = new SettingsPanel();
        final ColorPicker fillColorPicker = new ColorPicker(AppConfig.fill);
        fillColorPicker.getSelectionModel().addChangeListener(new ChangeListener() {
            @Override public void stateChanged(ChangeEvent e) {
                AppConfig.fill = fillColorPicker.getColor();
                if (AppConfig.selected != null && AppConfig.selected instanceof ShapeDrawing) {
                    ShapeDrawing shape = (ShapeDrawing)AppConfig.selected;
                    shape.setStyle(AppConfig.fill, shape.getStyle().getLineColor(), new BasicStroke(AppConfig.stroke));
                    AppConfig.drawingArea.repaint();
                }
            }
        });
        final ColorPicker lineColorPicker = new ColorPicker(AppConfig.line);
        lineColorPicker.getSelectionModel().addChangeListener(new ChangeListener() {
            @Override public void stateChanged(ChangeEvent e) {
                AppConfig.line = lineColorPicker.getColor();
                if (AppConfig.selected != null && AppConfig.selected instanceof ShapeDrawing) {
                    ShapeDrawing shape = (ShapeDrawing)AppConfig.selected;
                    shape.setStyle(shape.getStyle().getFillColor(), AppConfig.line, new BasicStroke(AppConfig.stroke));
                    AppConfig.drawingArea.repaint();
                }
            }
        });
        // attach popups
        UIToolbox.attachPopupPane(buttons.get("DRAW")     , drawingModePane, modeGroup, PopupPane.CENTER, false);
        UIToolbox.attachPopupPane(buttons.get("SETTINGS") , settingPane    , null     , PopupPane.RIGHT , true );
        UIToolbox.attachPopupPane(buttons.get("FILL")     , fillColorPicker, null     , PopupPane.LEFT  , true );
        UIToolbox.attachPopupPane(buttons.get("LINE")     , lineColorPicker, null     , PopupPane.LEFT  , true );
    }

} // MainToolbar

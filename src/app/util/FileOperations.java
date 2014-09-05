package app.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import app.component.ImageFileChooser;
import app.drawing.Drawing;
import app.drawing.ImageDrawing;
import app.main.AppConfig;

public class FileOperations {

    public static int newDrawing() {
        if (saveDrawing(true, false) == JOptionPane.CANCEL_OPTION) {
            return JOptionPane.CANCEL_OPTION;
        }
        AppConfig.file = null;
        if (!AppConfig.drawings.isEmpty()) {
            AppConfig.drawings.clear();
            AppConfig.preview = null;
            AppConfig.selected = null;
            AppConfig.drawingArea.repaint();
        }
        return JOptionPane.YES_OPTION;
    }

    public static int openDrawing() {
        if (newDrawing() == JOptionPane.CANCEL_OPTION) {
            return JOptionPane.CANCEL_OPTION;
        }
        ImageFileChooser imgChooser = new ImageFileChooser();
        int returnVal = imgChooser.showOpenDialog(null);
        if (returnVal == ImageFileChooser.APPROVE_OPTION) {
            AppConfig.drawings.add(new ImageDrawing(imgChooser.getSelectedFileWithExtension(), 0, 0));
            AppConfig.file = imgChooser.getSelectedFileWithExtension();
            String[] parts = AppConfig.file.getName().split("[.]");
            AppConfig.ext = parts[parts.length - 1];
            AppConfig.preview = null;
            AppConfig.selected = null;
            AppConfig.drawingArea.repaint();
            return JOptionPane.YES_OPTION;
        }
        return JOptionPane.CANCEL_OPTION;
    }
    
    private static int saveDrawing(File saveFile, String ext) {
        JPanel drawPanel = AppConfig.drawingArea;
        BufferedImage image = new BufferedImage(drawPanel.getWidth(), drawPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = image.createGraphics(); 
        boolean showGrid = AppConfig.isGrid;
        Drawing selected = AppConfig.selected;
        AppConfig.isGrid = false;
        AppConfig.selected = null;
        drawPanel.paint(graphics2D);
        AppConfig.isGrid = showGrid;
        AppConfig.selected = selected;
        try {
            ImageIO.write(image, ext, saveFile);
        } catch(Exception ex){
            ex.printStackTrace();
            return JOptionPane.NO_OPTION;
        }
        return JOptionPane.YES_OPTION;
    }

    public static int saveDrawing(boolean confirm, boolean saveAs) {
        if (AppConfig.drawings.isEmpty()) {
            return JOptionPane.YES_OPTION;
        }
        if (confirm) {
            int n = JOptionPane.showOptionDialog(null, "Do you to save changes to " + 
                    (AppConfig.file == null ? "Untitled" : AppConfig.file.getName()) + "?", "Save",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, null, null);
            if (n != JOptionPane.YES_OPTION) {
                return n;
            }
        }
        if (AppConfig.file == null || saveAs) {
            ImageFileChooser imgChooser = new ImageFileChooser();
            int returnVal = imgChooser.showSaveDialog(null);
            if (returnVal == ImageFileChooser.APPROVE_OPTION) {
                AppConfig.file = imgChooser.getSelectedFileWithExtension();
                AppConfig.ext = ((FileNameExtensionFilter)imgChooser.getFileFilter()).getExtensions()[0];
                return saveDrawing(AppConfig.file, AppConfig.ext);
            }
        } else {
            return saveDrawing(AppConfig.file, AppConfig.ext);
        }
        return JOptionPane.CANCEL_OPTION;
    }

} // Syscalls

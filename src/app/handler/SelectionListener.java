package app.handler;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import app.drawing.Drawing;
import app.drawing.ImageDrawing;
import app.drawing.ShapeDrawing;
import app.main.AppConfig;

public class SelectionListener extends MouseAdapter {
    private Component comp;

    public SelectionListener(Component comp) {
        this.comp = comp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        boolean selected = false;
        for (int i = AppConfig.drawings.size() - 1; i >= 0; i--) {
            Drawing d = AppConfig.drawings.get(i);
            Rectangle2D bound = d.getBounds2D();
            if (bound.contains(e.getX()/AppConfig.zoom, e.getY()/AppConfig.zoom)) {
                if (AppConfig.selected == d) {
                    AppConfig.selected = null;
                } else {
                    AppConfig.selected = d;
                }
                selected = true;
                break;
            }
        }
        if (!selected) {
            AppConfig.selected = null;
        }
        comp.repaint();
    }

    private double startx;
    private double starty;

    @Override
    public void mousePressed(MouseEvent e) {
        AppConfig.preview = null;
        double ptx = e.getX()/AppConfig.zoom;
        double pty = e.getY()/AppConfig.zoom;
        if (AppConfig.selected != null && 
                AppConfig.selected.getBounds2D().contains(ptx, pty)) {
            startx = ptx;
            starty = pty;
            if (AppConfig.selected instanceof ShapeDrawing) {
                AppConfig.preview = new ShapeDrawing((ShapeDrawing)AppConfig.selected);
            } else if (AppConfig.selected instanceof ImageDrawing) {
                AppConfig.preview = new ImageDrawing((ImageDrawing)AppConfig.selected);
            }
        }
        comp.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        double endx = e.getX()/AppConfig.zoom;
        double endy = e.getY()/AppConfig.zoom;
        if (AppConfig.selected != null && AppConfig.preview != null) {
            Drawing drawing = null;
            if (AppConfig.selected instanceof ShapeDrawing) {
                drawing = new ShapeDrawing((ShapeDrawing)AppConfig.selected);
            } else if (AppConfig.selected instanceof ImageDrawing) {
                drawing = new ImageDrawing((ImageDrawing)AppConfig.selected);
            }
            drawing.translate(endx - startx, endy - starty);
            AppConfig.preview = drawing;
        }
        comp.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        double endx = e.getX()/AppConfig.zoom;
        double endy = e.getY()/AppConfig.zoom;
        if (AppConfig.selected != null && AppConfig.preview != null && 
                AppConfig.preview.getBounds2D().contains(endx, endy)) {
            AppConfig.selected.translate(endx - startx, endy - starty);
            AppConfig.preview = null;
        }
        comp.repaint();
    }

} // DrawShapeListener
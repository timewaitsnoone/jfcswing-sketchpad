package app.handler;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import app.drawing.PathDrawing;
import app.drawing.ShapeDrawing;
import app.main.AppConfig;

public class DrawPolygonListener extends MouseAdapter {
    private PathDrawing path;
    private double startx;
    private double starty;
    private Component comp;
    private ShapeDrawing.Style shapeStyle;

    public DrawPolygonListener(Component comp) {
        this.comp = comp;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (path == null) {
            AppConfig.preview = null;
            startx = e.getX()/AppConfig.zoom;
            starty = e.getY()/AppConfig.zoom;
            path = new PathDrawing();
            path.moveTo(startx, starty);
            shapeStyle = new ShapeDrawing.Style();
                shapeStyle.setLineColor(AppConfig.line);
                shapeStyle.setStroke(new BasicStroke(AppConfig.stroke));
            path.setStyle(shapeStyle);
        } else {
            path.lineTo(e.getX()/AppConfig.zoom, e.getY()/AppConfig.zoom);
        }
        AppConfig.preview = path;
        comp.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (AppConfig.preview == null) {return;}
        PathDrawing p2 = new PathDrawing(path);
        p2.lineTo(e.getX()/AppConfig.zoom, e.getY()/AppConfig.zoom);
        p2.setStyle(shapeStyle);
        AppConfig.preview = p2;
        comp.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (AppConfig.preview == null) {return;}
        path = (PathDrawing) AppConfig.preview;
        if (Math.abs(startx - e.getX()*AppConfig.zoom) < 5 && Math.abs(starty - e.getY()*AppConfig.zoom) < 5) {
            shapeStyle.setFillColor(AppConfig.fill);
            path.closePath();
            path.setStyle(shapeStyle);
            AppConfig.drawings.add(path);
            AppConfig.preview = null;
            path = null;
        }
        comp.repaint();
    }

} // DrawPolygonListener
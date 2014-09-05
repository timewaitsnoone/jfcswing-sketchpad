package app.handler;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import app.drawing.EllipseDrawing;
import app.drawing.LineDrawing;
import app.drawing.PathDrawing;
import app.drawing.RectangleDrawing;
import app.drawing.RoundRectangleDrawing;
import app.drawing.ShapeDrawing;
import app.main.AppConfig;

public class DrawShapeListener extends MouseAdapter {
    private PathDrawing path;
    private double startx;
    private double starty;
    private Component comp;

    public DrawShapeListener(Component comp) {
        this.comp = comp;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        AppConfig.preview = null;
        startx = e.getX()/AppConfig.zoom;
        starty = e.getY()/AppConfig.zoom;
        switch (AppConfig.mode) {
            case FREEHAND:
                path = new PathDrawing();
                path.moveTo(startx, starty);
                AppConfig.preview = path;
                break;
            case LINE:      AppConfig.preview =    LineDrawing.createLine(startx, starty, 0, 0);       break;
            case OVAL:      AppConfig.preview =        new EllipseDrawing(startx, starty, 0, 0);       break;
            case RECTANGLE: AppConfig.preview =      new RectangleDrawing(startx, starty, 0, 0);       break;
            case ROUNDRECT: AppConfig.preview = new RoundRectangleDrawing(startx, starty, 0, 0, 0, 0); break;
            default:
                break;
        } // switch AppConfig.mode
        if (AppConfig.preview instanceof ShapeDrawing) {
            ShapeDrawing.Style shapeStyle = new ShapeDrawing.Style();
            if (AppConfig.mode != AppConfig.Mode.FREEHAND) {
                shapeStyle.setFillColor(AppConfig.fill);
            }
            shapeStyle.setLineColor(AppConfig.line);
            shapeStyle.setStroke(new BasicStroke(AppConfig.stroke));
            ((ShapeDrawing) AppConfig.preview).setStyle(shapeStyle);
        }
        comp.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (AppConfig.preview == null) {return;}        
        double endx = e.getX()/AppConfig.zoom;
        double endy = e.getY()/AppConfig.zoom;
        double x = (startx > endx) ? endx : startx;
        double y = (starty > endy) ? endy : starty;
        double w = Math.abs(endx - startx);
        double h = Math.abs(endy - starty);

        switch (AppConfig.mode) {
            case FREEHAND:
                path.lineTo(endx, endy);
                break;
            case LINE:      AppConfig.preview = new LineDrawing(startx, starty, endx, endy);           break;
            case OVAL:      AppConfig.preview =        new EllipseDrawing(x, y, w, h);                 break;
            case RECTANGLE: AppConfig.preview =      new RectangleDrawing(x, y, w, h);                 break;
            case ROUNDRECT: AppConfig.preview = new RoundRectangleDrawing(x, y, w, h, w*0.25, h*0.25); break;
            default:
                break;
        } // switch AppConfig.mode
        if (AppConfig.preview instanceof ShapeDrawing) {
            ShapeDrawing.Style shapeStyle = new ShapeDrawing.Style();
            if (AppConfig.mode != AppConfig.Mode.FREEHAND) {
                shapeStyle.setFillColor(AppConfig.fill);
            }
            shapeStyle.setLineColor(AppConfig.line);
            shapeStyle.setStroke(new BasicStroke(AppConfig.stroke));
            ((ShapeDrawing) AppConfig.preview).setStyle(shapeStyle);
        }
        comp.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (AppConfig.preview == null) {return;}
        switch (AppConfig.mode) {
            case FREEHAND: path = null;
            case LINE:  
            case OVAL:                  
            case RECTANGLE:                     
            case ROUNDRECT:
                AppConfig.drawings.add(AppConfig.preview);
                break;
            default:
                break;
        } // switch AppConfig.mode
        AppConfig.preview = null;
        comp.repaint();
    }

} // DrawShapeListener
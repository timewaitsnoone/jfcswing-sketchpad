package app.handler;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import app.handler.DrawPolygonListener;
import app.handler.DrawShapeListener;
import app.handler.SelectionListener;
import app.main.AppConfig;

public class DrawingListener extends MouseAdapter {

    private DrawShapeListener dsl;
    private SelectionListener sel;
    private DrawPolygonListener dpl;
    private ImageListener imgl;
    
    public DrawingListener(Component comp) {
        this.dsl = new DrawShapeListener(comp);
        this.sel = new SelectionListener(comp);
        this.dpl = new DrawPolygonListener(comp);
        this.imgl = new ImageListener(comp);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (AppConfig.mode) {
            case FREEHAND:
            case LINE:
            case OVAL:
            case RECTANGLE:
            case ROUNDRECT:
                dsl.mouseClicked(e);
                break;
            case POLYGON:
                dpl.mouseClicked(e);
                break;
            case ROTATE:
                break;
            case IMAGE:
                imgl.mouseClicked(e);
                break;
            case SELECT:
                sel.mouseClicked(e);
                break;
            case TEXT:
                break;
            default:
                break;
        } // switch
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (AppConfig.mode) {
            case FREEHAND:
            case LINE:
            case OVAL:
            case RECTANGLE:
            case ROUNDRECT:
                dsl.mousePressed(e);
                break;
            case POLYGON:
                dpl.mousePressed(e);
                break;
            case ROTATE:
                break;
            case IMAGE:
                imgl.mousePressed(e);
                break;
            case SELECT:
                sel.mousePressed(e);
                break;
            case TEXT:
                break;
            default:
                break;
        } // switch
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        switch (AppConfig.mode) {
            case FREEHAND:
            case LINE:
            case OVAL:
            case RECTANGLE:
            case ROUNDRECT:
                dsl.mouseDragged(e);
                break;
            case POLYGON:
                dpl.mouseDragged(e);
                break;
            case ROTATE:
                break;
            case IMAGE:
                imgl.mouseDragged(e);
                break;
            case SELECT:
                sel.mouseDragged(e);
                break;
            case TEXT:
                break;
            default:
                break;
        } // switch
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (AppConfig.mode) {
            case FREEHAND:
            case LINE:
            case OVAL:
            case RECTANGLE:
            case ROUNDRECT:
                dsl.mouseReleased(e);
                break;
            case POLYGON:
                dpl.mouseReleased(e);
                break;
            case ROTATE:
                break;
            case IMAGE:
                imgl.mouseReleased(e);
                break;
            case SELECT:
                sel.mouseReleased(e);
                break;
            case TEXT:
                break;
            default:
                break;
        } // switch
    }

} // DrawingListener

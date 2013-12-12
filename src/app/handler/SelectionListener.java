package app.handler;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import app.drawing.Drawing;
import app.drawing.PathDrawing;
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
				ShapeDrawing shape = (ShapeDrawing)AppConfig.selected;
				AppConfig.preview = new PathDrawing(shape.getShape());
			}
		}
		comp.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		double endx = e.getX()/AppConfig.zoom;
		double endy = e.getY()/AppConfig.zoom;
		if (AppConfig.selected != null && AppConfig.preview != null) {
			if (AppConfig.selected instanceof ShapeDrawing) {
				ShapeDrawing shape = (ShapeDrawing)AppConfig.selected;
				PathDrawing path = new PathDrawing(shape.getShape());
				Color fill = shape.getStyle().getFillColor();
				if (fill != null) {
					fill = new Color(fill.getRed(),
									   fill.getGreen(),
									   fill.getBlue(),
									   (int)(0.5*fill.getAlpha()));
				}
				Color line = shape.getStyle().getLineColor();
				if (line != null) {
					line = new Color(line.getRed(),
									   line.getGreen(),
									   line.getBlue(),
									   (int)(0.5*line.getAlpha()));
				}
				path.setStyle(fill, line, shape.getStyle().getStroke());
				path.translate(endx - startx, endy - starty);
				AppConfig.preview = path;
			}
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
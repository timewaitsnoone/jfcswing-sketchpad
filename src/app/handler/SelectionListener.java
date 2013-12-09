package app.handler;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
			if (d.contains(e.getPoint())) {
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
		startx = 0;
		starty = 0;
		AppConfig.preview = null;
		if (AppConfig.selected != null) {
			startx = e.getX();
			starty = e.getX();
		}
		comp.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (AppConfig.selected != null) {
			double endx = e.getX();
			double endy = e.getY();
			if (AppConfig.selected instanceof ShapeDrawing) {
				ShapeDrawing shape = (ShapeDrawing)AppConfig.selected;
				PathDrawing path = new PathDrawing(shape.getShape());
				Color fill = shape.getStyle().getFillColor();
				if (fill != null) {
					fill = new Color(AppConfig.fill.getRed(),
									   AppConfig.fill.getGreen(),
									   AppConfig.fill.getBlue(),
									   (int)(0.5*AppConfig.fill.getAlpha()));
				}
				Color line = shape.getStyle().getLineColor();
				if (line != null) {
					line = new Color(AppConfig.line.getRed(),
									   AppConfig.line.getGreen(),
									   AppConfig.line.getBlue(),
									   (int)(0.5*AppConfig.line.getAlpha()));
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
		if (AppConfig.selected != null) {
			double endx = e.getX();
			double endy = e.getY();
			AppConfig.selected.translate(endx - startx, endy - starty);
			AppConfig.preview = null;
		}
		startx = 0;
		starty = 0;
		comp.repaint();
	}

} // DrawShapeListener
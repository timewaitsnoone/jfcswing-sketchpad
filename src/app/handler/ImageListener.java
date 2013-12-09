package app.handler;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import app.drawing.ImageDrawing;
import app.main.AppConfig;

public class ImageListener extends MouseAdapter {
	private double startx;
	private double starty;
	private Component comp;
	
	@Override
	public void mousePressed(MouseEvent e) {
		startx = e.getX();
		starty = e.getY();
		ImageDrawing image = new ImageDrawing(AppConfig.imagePath, startx, starty);
		AppConfig.preview = image;
		comp.repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		double endx = e.getX();
		double endy = e.getY();
		double w = endx - startx;
		double h = endy - starty;
		Rectangle2D bound = AppConfig.preview.getBounds2D();
		AppConfig.preview.scaleOnCenter(w/bound.getWidth(), h/bound.getHeight());
		comp.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		AppConfig.drawings.add(AppConfig.preview);
		AppConfig.preview = null;
		comp.repaint();
	}

	public ImageListener(Component comp) {
		this.comp = comp;
	}
}

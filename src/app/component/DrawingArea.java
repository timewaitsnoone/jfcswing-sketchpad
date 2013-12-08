package app.component;

import app.handler.*;
import app.drawing.*;
import app.main.AppConfig;
import app.util.UIToolbox;

import java.awt.*;

import javax.swing.*;

public class DrawingArea extends JPanel {

	public DrawingArea() {
		setPreferredSize(AppConfig.size);
		DrawingListener dl = new DrawingListener(this);
		addMouseListener(dl);
		addMouseMotionListener(dl);
	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		paintBacking(g);
		paintDrawing(g);
		paintPreview(g);
		paintSelection(g);
	}

	private void paintBacking(Graphics g) {
		Graphics2D g2d = (Graphics2D)g.create();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			if (AppConfig.isOpaque) {setBackground(Color.WHITE);}
			if (AppConfig.isGrid) {UIToolbox.drawGrid(g2d);}
		g2d.dispose();
	}

	private void paintDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D)g.create();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			for (Drawing d : AppConfig.drawings) {
				d.draw(g2d);
			}
		g2d.dispose();
	}

	private void paintPreview(Graphics g) {
		Graphics2D g2d = (Graphics2D)g.create();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			if (AppConfig.preview != null) {
				AppConfig.preview.draw(g2d);
			}
		g2d.dispose();
	}

	private void paintSelection(Graphics g) {
		Graphics2D g2d = (Graphics2D)g.create();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			for (Drawing d : AppConfig.selected) {
				g2d.setColor(Color.red);
				g2d.draw(d.getBounds2D());
			}
		g2d.dispose();
	}

} // DrawingArea

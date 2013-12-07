package app.tests;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import app.component.DrawingViewport;
import app.model.Sketch;
import app.model.drawing.Drawing;
import app.model.drawing.shape.ArcDrawing;
import app.model.drawing.shape.CubicCurveDrawing;
import app.model.drawing.shape.EllipseDrawing;
import app.model.drawing.shape.LineDrawing;
import app.model.drawing.shape.PathDrawing;
import app.model.drawing.shape.QuadCurveDrawing;
import app.model.drawing.shape.RectangleDrawing;
import app.model.drawing.shape.RightTriangleDrawing;
import app.model.drawing.shape.RoundRectangleDrawing;
import app.model.drawing.shape.TriangleDrawing;
import app.util.UITheme;

public class testSketch {
	public static void main(String[] args) {
		UITheme.setLookAndFeel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			final Sketch sketch = new Sketch(new Dimension(600, 600), Color.WHITE);
				sketch.add((new ArcDrawing(100.0, 100.0, 400.0, 400.0, 0.0, 180.0, 1))
					.setStroke(Color.RED, new BasicStroke(2.f)));
				sketch.add((new CubicCurveDrawing(50.0, 50.0, 100.0, 300.0, 300.0, 100.0, 400.0, 200.0))
					.setStroke(Color.BLUE, new BasicStroke(2.f)));
				sketch.add((new EllipseDrawing(350.0, 350.0, 200.0, 200.0))
					.setStroke(Color.GREEN, new BasicStroke(2.f)));
				sketch.add(EllipseDrawing.createEllipse(150.0, 450.0, 100.0, 100.0)
					.setStroke(Color.GREEN, new BasicStroke(2.f)));
				sketch.add(LineDrawing.createLine(150.0, 450.0, 300.0, 0.0)
					.setStroke(Color.ORANGE, new BasicStroke(2.f)));
				sketch.add((new PathDrawing())
					.moveTo(100, 300)
					.lineTo(500, 300)
					.lineTo(450, 450)
					.closePath()
					.moveTo(100, 300)
					.lineTo(500, 300)
					.lineTo(150, 450)
					.closePath()
					.setStroke(Color.CYAN, new BasicStroke(2.f)));
				sketch.add((new QuadCurveDrawing(100.0, 304.0, 300.0, 700.0, 500.0, 304.0))
					.setStroke(Color.MAGENTA, new BasicStroke(2.f)));
				sketch.add((new RectangleDrawing(200.0, 300.0, 200.0, 150.0))
					.setStroke(Color.PINK, new BasicStroke(2.f)));
				sketch.add((new RoundRectangleDrawing(210.0, 310.0, 180.0, 130.0, 50.0, 50.0))
					.setStroke(Color.YELLOW, new BasicStroke(2.f)));
				sketch.add((new TriangleDrawing(100.0, 100.0, 400.0, 200.0))
					.setStroke(Color.LIGHT_GRAY, new BasicStroke(2.f)));
				sketch.add((new RightTriangleDrawing(300.0, 100.0, 200.0, 200.0))
					.setStroke(Color.GRAY, new BasicStroke(2.f)));
				sketch.resize(new Dimension(800, 400));
			JPanel pp = new JPanel() {
				@Override public void paint(Graphics g) {
					super.paint(g);
					Graphics2D g2d = (Graphics2D)g;
					g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					for (Drawing d : sketch) {
						d.draw(g2d);
					}
				} // paint
			};
			pp.setBackground(sketch.getBackground());
			pp.setPreferredSize(sketch.getSize());
		DrawingViewport dv = new DrawingViewport(pp);
		frame.add(dv);
		frame.pack();
		frame.setVisible(true);
	}
} // testSketch

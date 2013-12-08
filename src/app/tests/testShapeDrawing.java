package app.tests;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import app.component.DrawingViewport;
import app.drawing.*;
import app.util.UITheme;

public class testShapeDrawing {
	public static void main(String[] args) {
		UITheme.setLookAndFeel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			JPanel pp = new JPanel() {
				@Override public void paint(Graphics g) {
					super.paint(g);
					Graphics2D g2d = (Graphics2D)g;
					g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					(new ArcDrawing(100.0, 100.0, 400.0, 400.0, 0.0, 180.0, 1))
						.setStyle(Color.RED, Color.RED, new BasicStroke(2.f))
						.draw(g2d);
					(new CubicCurveDrawing(50.0, 50.0, 100.0, 300.0, 300.0, 100.0, 400.0, 200.0))
						.setStyle(Color.BLUE, Color.BLUE, new BasicStroke(2.f))
						.draw(g2d);
					(new EllipseDrawing(350.0, 350.0, 200.0, 200.0))
						.setStyle(Color.GREEN, Color.GREEN, new BasicStroke(2.f))
						.draw(g2d);
					EllipseDrawing.createEllipse(150.0, 450.0, 100.0, 100.0)
						.setStyle(Color.GREEN, Color.GREEN, new BasicStroke(2.f))
						.draw(g2d);
					LineDrawing.createLine(150.0, 450.0, 300.0, 0.0)
						.setStyle(Color.ORANGE, Color.ORANGE, new BasicStroke(2.f))
						.draw(g2d);
					(new PathDrawing())
						.moveTo(100, 300)
						.lineTo(500, 300)
						.lineTo(450, 450)
						.closePath()
						.moveTo(100, 300)
						.lineTo(500, 300)
						.lineTo(150, 450)
						.closePath()
						.setStyle(Color.CYAN, Color.CYAN, new BasicStroke(2.f))
						.draw(g2d);
					(new QuadCurveDrawing(100.0, 304.0, 300.0, 700.0, 500.0, 304.0))
						.setStyle(Color.MAGENTA, Color.MAGENTA, new BasicStroke(2.f))
						.draw(g2d);
					(new RectangleDrawing(200.0, 300.0, 200.0, 150.0))
 						.setStyle(Color.PINK, Color.PINK, new BasicStroke(2.f))
 						.draw(g2d);
 					(new RoundRectangleDrawing(210.0, 310.0, 180.0, 130.0, 50.0, 50.0))
 						.setStyle(Color.YELLOW, Color.YELLOW, new BasicStroke(2.f))
						.draw(g2d);
					(new TriangleDrawing(100.0, 100.0, 400.0, 200.0))
						.setStyle(Color.LIGHT_GRAY, Color.LIGHT_GRAY, new BasicStroke(2.f))
						.draw(g2d);
					(new RightTriangleDrawing(300.0, 100.0, 200.0, 200.0))
						.setStyle(Color.GRAY, Color.GRAY, new BasicStroke(2.f))
						.draw(g2d);
				} // paint
			};
			pp.setBackground(Color.WHITE);
			pp.setPreferredSize(new Dimension(600, 600));
		DrawingViewport dv = new DrawingViewport(pp);
		frame.add(dv);
		frame.pack();
		frame.setVisible(true);
	}
} // testShapeDrawing

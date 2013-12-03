package app.tests;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import app.components.DrawingViewport;
import app.util.UITheme;

public class testDrawingViewport {
	public static void main(String[] args) {
		UITheme.setLookAndFeel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			JPanel pp = new JPanel() {
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D)g;
					g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					int[][] posn = {{300, 150}, {150, 100}, {250, 300}, {100, 250}};
					Color[] col1 = {Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE};
					for (int i = 0; i < 4; i++) {
						Graphics2D g2 = (Graphics2D)g2d.create();
						g2.setPaint(new GradientPaint(
							posn[i][0], posn[i][1],
							col1[i],
							posn[i][0] + 100, posn[i][1] + 100,
							col1[i].darker().darker().darker()));
						g2.fill(new Ellipse2D.Double(posn[i][0], posn[i][1], 200, 200));
						g2.dispose();
					}
				} // paintComponent
			}; // JPanel pp
			pp.setBackground(Color.WHITE);
			pp.setPreferredSize(new Dimension(600, 600));
			DrawingViewport dv = new DrawingViewport(pp);
		frame.add(dv);
		frame.pack();
		frame.setVisible(true);
	}
} // testDrawingViewport

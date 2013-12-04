package app.tests;

import java.awt.*;
import javax.swing.*;
import app.component.*;
import app.document.*;
import app.drawing.shape.*;
import app.util.*;

public class testPaintCanvas {
	public static void main(String[] args) {
		UITheme.setLookAndFeel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Document doc = new Document(new Dimension(680, 400), Color.WHITE);
			doc.add((new RectangleDrawing(100, 100, 100, 100)).setFill(Color.RED));
			doc.add((new RectangleDrawing(120, 130, 100, 100)).setFill(Color.GREEN));
			doc.add((new RectangleDrawing(140, 160, 100, 100)).setFill(Color.GREEN));
		frame.add(new DrawingViewport(new PaintCanvas(doc)));
		frame.setMinimumSize(new Dimension(600, 480));
		frame.setPreferredSize(new Dimension(800, 600));
		frame.pack();
		frame.setVisible(true);
	}
} // testPaintCanvas

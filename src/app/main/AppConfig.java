package app.main;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import app.drawing.Drawing;
import app.drawing.RectangleDrawing;

public class AppConfig {
	
	public static enum Mode {
		SELECT, ROTATE,
		LINE, RECTANGLE, ROUNDRECT, OVAL,
		FREEHAND, POLYGON, IMAGE, TEXT 
	}

	// Settings

	public static Mode mode = Mode.SELECT;
	public static Color fill = null;
	public static Color line = Color.BLACK;
	public static int stroke = 1; 
	public static boolean isOpaque = true;
	public static boolean isGrid = true;
	public static boolean isRuler = false;
	public static double zoom = 1.0;
	public static Dimension size = new Dimension(600, 600);
	public static List<Drawing> drawings = new ArrayList<Drawing>();
	public static Drawing preview = null;
	public static List<Drawing> selected = new ArrayList<Drawing>();
	public static RectangleDrawing selectedBox = null;

} // AppConfig

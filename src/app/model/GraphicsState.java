package app.model;

import java.awt.*;
import java.awt.geom.*;

/**
 * This class provides a stack frame for
 * storing the state of the graphics context.
 * 
 * The stored data is:
 * 	 - background
 *   - foreground
 *   - clip
 *   - composite
 *   - font
 *   - paint
 *   - stroke
 *   - transform 
 */
public class GraphicsState {

	private Color 			background;
	private Color 			foreground;
	private Shape 			clip;
	private Composite 		composite;
	private Font 			font;
	private Paint 			paint;
	private Stroke 			stroke;
	private AffineTransform transform;

	public GraphicsState(Graphics2D g) {
		background = g.getBackground();
		clip 	   = g.getClip();
		foreground = g.getColor();
		composite  = g.getComposite();
		font       = g.getFont();
		paint 	   = g.getPaint();
		stroke	   = g.getStroke();
		transform  = g.getTransform();
	}

	public Color 		   getBackground() {return background;}
	public Color 		   getForeground() {return foreground;}
	public Shape 		   getClip() 	   {return clip;}
	public Composite 	   getComposite()  {return composite;}
	public Font 		   getFont() 	   {return font;}
	public Paint 		   getPaint() 	   {return paint;}
	public Stroke 		   getStroke() 	   {return stroke;}
	public AffineTransform getTransform()  {return transform;}
}

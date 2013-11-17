package app.model;

import java.awt.*;
import java.util.*;

/**
 * This class provides a graphics context stack.
 * Enables the programmer to protect changes in the graphics
 * context in one block of code from another.
 * Example:
 * 
 * 		gs.Push(); {
 * 			g.setColor(Color.RED);
 * 			g.rotate(0.25);
 * 			g.fill(new Rectangle2D.Float(10f, 10f, 20f, 20f));
 * 		} gs.Pop();
 * 		gs.Push(); {
 * 			g.setColor(Color.BLACK);
 * 			g.scale(0.5);
 * 			g.fill(new Ellipse2D.Float(10f, 10f, 20f, 20f));
 * 		} gs.Pop();
 */
public class GraphicsStack extends Stack<GraphicsState> {
	private final Graphics2D G2D;
	public GraphicsStack(Graphics2D g) {G2D = g;}

	public GraphicsState push() {
		return super.push(new GraphicsState(G2D));}

	@Override public GraphicsState pop() {
		GraphicsState oldGS = super.pop();
		GraphicsState gs = super.peek();
		G2D.setBackground(gs.getBackground());
		G2D.setColor(gs.getForeground());
		G2D.setClip(gs.getClip());
		G2D.setComposite(gs.getComposite());
		G2D.setFont(gs.getFont());
		G2D.setPaint(gs.getPaint());
		G2D.setStroke(gs.getStroke());
		G2D.setTransform(gs.getTransform());
		return oldGS;}
}

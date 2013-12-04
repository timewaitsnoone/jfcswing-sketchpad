package app.component;

import javax.swing.*;
import app.controller.*;
import app.document.*;
import app.drawing.*;
import java.awt.*;

/**
 * This class implements the paint canvas.
 */
public class PaintCanvas extends JPanel {
	
	private double zoomPercent = 1.0;
	private final Document doc;
	private Drawing selected = null;

	/**
	 * Constructs a new paint canvas for
	 * the given document.
	 * 
	 * @param doc	the document.
	 */
	public PaintCanvas(Document doc) {
		this.doc = doc;
		addMouseListener(new CanvasEventHandler(this));
		setPreferredSize(doc.getSize());
		setBackground(doc.getBackground());
	}

	/**
	 * Returns the document.
	 * 
	 * @return the document.
	 */
	public Document getDocument() {
		return doc;
	}

	/**
	 * Returns the selected drawing.
	 * 
	 * @return the selected drawing.
	 */
	public Drawing getSelected() {
		return selected;
	}
	
	/**
	 * Determines if a drawing is selected.
	 * 
	 * @return true if a drawing is selected, otherwise false.
	 */
	public boolean isSelected() {
		return selected != null;
	}

	/**
	 * Sets the selected drawing.
	 * 
	 * @param selected 		the selected drawing.
	 */
	public void setSelected(Drawing selected) {
		this.selected = selected;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.scale(zoomPercent, zoomPercent);
		for (Drawing d : doc) {
			d.draw(g2D);
		}
	}

} // PaintCanvas
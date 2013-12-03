package app.components;

import java.awt.*;
import javax.swing.*;

/**
 * This class implements a viewpoint for the
 * drawing area. Allows the user to pan, scroll
 * and zoom in or out of the drawing.
 */
public class DrawingViewport extends JScrollPane {
    /**
     * Creates a <code>JScrollPane</code> that displays the
     * contents of the specified
     * component, where both horizontal and vertical scrollbars appear
     * whenever the component's contents are larger than the view.
     *
     * @see #setViewportView
     * @param pane the component to display in the scrollpane's viewport
     */
	public DrawingViewport(JPanel pane) {
		Dimension d = pane.getPreferredSize();
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		getVerticalScrollBar().setUnitIncrement(25);
		getHorizontalScrollBar().setUnitIncrement(25);
			JPanel inner = new JPanel();
			inner.setLayout(new GridBagLayout());
			inner.setPreferredSize(new Dimension(d.width + 50, d.height + 50));
			inner.add(pane);
		setViewportView(inner);
	}
} // DrawingViewport

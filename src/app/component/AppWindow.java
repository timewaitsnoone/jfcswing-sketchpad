package app.component;

import java.awt.*;
import javax.swing.*;

public class AppWindow extends JFrame {

	public AppWindow() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().add(new MainToolbar(), BorderLayout.NORTH);
		getContentPane().add(new DrawingViewport(), BorderLayout.CENTER);
		setMinimumSize(new Dimension(600, 480));
		setPreferredSize(new Dimension(800, 600));
		pack();
	}

    /**
     * This class implements a viewpoint for the
     * drawing area. Allows the user to pan, scroll
     * and zoom in or out of the drawing.
     */
    private static class DrawingViewport extends JScrollPane {
        private DrawingArea drawingArea = new DrawingArea();
        /**
         * Creates a <code>JScrollPane</code> that displays the
         * contents of the specified
         * component, where both horizontal and vertical scrollbars appear
         * whenever the component's contents are larger than the view.
         *
         * @see #setViewportView
         * @param pane the component to display in the scrollpane's viewport
         */
        public DrawingViewport() {
            Dimension d = drawingArea.getPreferredSize();
            setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            getVerticalScrollBar().setUnitIncrement(25);
            getHorizontalScrollBar().setUnitIncrement(25);
                JPanel inner = new JPanel();
                inner.setLayout(new GridBagLayout());
                inner.setPreferredSize(new Dimension(d.width + 50, d.height + 50));
                inner.add(drawingArea);
            setViewportView(inner);
        }
    } // DrawingViewport
    
} // AppWindow
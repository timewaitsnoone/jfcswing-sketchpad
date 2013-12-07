package app.component;

import java.util.*;
import java.awt.*;	
import java.awt.geom.*;
import javax.swing.*;

/**
 * This class implements a custom combo box
 * for display a selection of strokes.
 */
public class StrokeComboBox extends JComboBox<Stroke> {
	private Map<Stroke, Shape> strokes = new HashMap<Stroke, Shape>();
	public StrokeComboBox() {
		setRenderer(new StrokeItemRenderer());
		setMaximumRowCount(10);
	}
	private int getComboBoxWidth() {
		return getWidth();
	}
	private class StrokeItemRenderer
            extends JPanel
            implements ListCellRenderer<Stroke> {
		private Stroke stroke;
		public StrokeItemRenderer() {
            setOpaque(true);
            Dimension d = new Dimension(150, 25);
            setMinimumSize(d);
            setMaximumSize(d);
            setPreferredSize(d);
        }
		@Override public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D)g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(getForeground());
			int y = (int) getHeight() / 2;
			Line2D line = new Line2D.Double(20, y, getComboBoxWidth() - 30, y);
			if (!strokes.containsKey(stroke)) {
				strokes.put(stroke, stroke.createStrokedShape(line));
			}
			g2d.fill(strokes.get(stroke));
		} // paintComponent
		@Override public Component getListCellRendererComponent(
				JList<? extends Stroke> list, Stroke value, int index,
				boolean isSelected, boolean cellHasFocus) {
			if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
			this.stroke = value;
			this.repaint();
			return this;
		}
	}  // StrokeItemRenderer
} // StokeComboBox

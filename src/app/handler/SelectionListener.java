package app.handler;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectionListener extends MouseAdapter {
	private Component comp;

	public SelectionListener(Component comp) {
		this.comp = comp;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		comp.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {

		comp.repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {

		comp.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		comp.repaint();
	}

} // DrawShapeListener
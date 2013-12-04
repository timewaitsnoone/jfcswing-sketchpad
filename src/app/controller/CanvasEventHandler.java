package app.controller;

import app.component.*;
import app.document.*;
import app.drawing.*;
import java.awt.event.*;

public class CanvasEventHandler implements MouseListener {

	public static final int SELECT 		    = 0;
	public static final int SELECTED 		= 1;
	public static final int DRAW_BOUND 		= 2;
	public static final int DRAW_FREEHAND  	= 3;
	public static final int DRAW_POLYGON   	= 4;
	public static final int ROTATE         	= 5;
	
	private final PaintCanvas CANVAS;
	private final Document DOCUMENT;

	private final MouseListener[] HANDLERS = { 
		new MouseAdapter() {
			@Override public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				for (int i = DOCUMENT.size() - 1; i >= 0; i--) {
					Drawing drawing = DOCUMENT.get(i);
					if (drawing.contains(x, y)) {
						CANVAS.setSelected(drawing);
					}
				} // for
				CANVAS.setSelected(null);
			}
		},
	}; // HANDLERS

	private int mode = SELECT;
	
	public CanvasEventHandler(PaintCanvas canvas) {
		this.CANVAS = canvas;
		this.DOCUMENT = CANVAS.getDocument();
	}

	@Override public void mouseClicked(MouseEvent e) {
		HANDLERS[mode].mouseClicked(e);}
	@Override public void mouseEntered(MouseEvent e) {
		HANDLERS[mode].mouseEntered(e);}
	@Override public void mouseExited(MouseEvent e) {
		HANDLERS[mode].mouseExited(e);}
	@Override public void mousePressed(MouseEvent e) {
		HANDLERS[mode].mousePressed(e);}
	@Override public void mouseReleased(MouseEvent e) {
		HANDLERS[mode].mouseReleased(e);}

} // CanvasEventHandler

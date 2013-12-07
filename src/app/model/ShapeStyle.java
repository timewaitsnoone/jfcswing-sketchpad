package app.model;

import java.awt.*;

/**
 * This class defines a set of styling
 * properties for shape drawings.
 */
public class ShapeStyle {

	private Paint fill;
	private Paint line;
	private Stroke lineStyle;

	// Getter
	
	public Paint getFill() {return fill;}
	public Paint getLine() {return line;}
	public Stroke getLineStyle() {return lineStyle;}
	
	// Setter
	
	public void setFill(Paint fill) {this.fill = fill;}
	public void setLine(Paint line) {this.line = line;}
	public void setLineStyle(Stroke lineStyle) {this.lineStyle = lineStyle;}

} // ShapeStyle

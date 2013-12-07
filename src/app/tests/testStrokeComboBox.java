package app.tests;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import app.component.StrokeComboBox;
import app.util.UITheme;
import app.model.drawing.shape.*;
import app.model.stroke.*;

public class testStrokeComboBox {
    public static void main(String[] args) {
        UITheme.setLookAndFeel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel outer = new JPanel(new GridBagLayout());
            StrokeComboBox combo = new StrokeComboBox();
                combo.addItem(new BasicStroke());
                combo.addItem(new CompositeStroke(new BasicStroke(5), new BasicStroke(2)));
                combo.addItem(new ShapeStroke(new TriangleDrawing(0, 0, 10, 10).getShape(), 20.0f));
                combo.addItem(new WobbleStroke(2.0f, 2.0f));
                combo.addItem(new ZigzagStroke(new BasicStroke(), 4.0f, 4.0f));
            outer.add(combo);
        frame.add(outer);
        frame.pack();
        frame.setVisible(true);
    }
} // testStrokeComboBox

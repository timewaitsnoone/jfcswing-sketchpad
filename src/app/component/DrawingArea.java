package app.component;

import app.handler.*;
import app.drawing.*;
import app.main.AppConfig;
import app.util.UIToolbox;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class DrawingArea extends JPanel {

    public DrawingArea() {
        setPreferredSize(AppConfig.size);
        DrawingListener dl = new DrawingListener(this);
        addMouseListener(dl);
        addMouseMotionListener(dl);
    }

    public void zoom() {
        int width  = (int)(AppConfig.zoom*AppConfig.size.width);
        int height = (int)(AppConfig.zoom*AppConfig.size.height);
        setPreferredSize(new Dimension(width, height));
        getParent().setPreferredSize(new Dimension(width + 50, height + 50));
        revalidate();
        repaint();
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D)g).scale(AppConfig.zoom, AppConfig.zoom);
        paintBacking(g);
        paintDrawing(g);
        paintGrid(g);
        paintPreview(g);
        paintSelection(g);
    }

    private void paintBacking(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (AppConfig.isOpaque) {setBackground(Color.WHITE);}
        g2d.dispose();
    }

    private void paintDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            for (Drawing d : AppConfig.drawings) {
                d.draw(g2d);
            }
        g2d.dispose();
    }

    private void paintGrid(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (AppConfig.isGrid) {UIToolbox.drawGrid(g2d);}
        g2d.dispose();
    }

    private void paintPreview(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (AppConfig.preview != null) {
                g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f));
                AppConfig.preview.draw(g2d);
                g2d.setColor(Color.red);
                g2d.draw(AppConfig.preview.getBounds2D());
            }
        g2d.dispose();
    }

    private void paintSelection(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (AppConfig.selected != null) {
                Rectangle2D bound = AppConfig.selected.getBounds2D();
                bound = new Rectangle2D.Double(bound.getX() - 5, bound.getY() - 5, bound.getWidth() + 10, bound.getHeight() + 10);
                g2d.setColor(Color.RED);
                g2d.draw(bound);
            }
        g2d.dispose();
    }

} // DrawingArea

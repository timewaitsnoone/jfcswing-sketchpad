package app.main;

import app.component.AppWindow;
import app.util.UITheme;

public class Main {
    public static void main(String[] args) {
        UITheme.setLookAndFeel();
        AppWindow app = new AppWindow();
        app.setVisible(true);
    }
} // Main

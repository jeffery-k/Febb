package febb.display;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    private static final int PANEL_WIDTH = 300;
    private static final int PANEL_HEIGHT = 200;

    private String name;
    private int panelCount;
    private Display display;

    public View() {
        super(new BorderLayout());
        //TODO
    }

    public View(String name, int panelCount) {
        this.name = name;
        this.panelCount = panelCount;
    }

    public void load() {
        //TODO
    }

    public void dump() {
        //TODO
    }
}

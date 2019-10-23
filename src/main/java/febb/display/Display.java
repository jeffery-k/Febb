package febb.display;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Display {
    private static final int PANEL_WIDTH = 300;
    private static final int PANEL_HEIGHT = 200;
    private static final int BUTTON_WIDTH = 60;
    private static final int BUTTON_HEIGHT = 40;
    private static final String MAIN_VIEW_NAME = "Main";

    private String name;
    private List<View> views;
    private JFrame frame;
    public Display(String name) {
        this.name = name;
        this.views = new ArrayList<View>();
        views.add(new View(MAIN_VIEW_NAME,1));
        init();
    }

    //TODO
    private void init() {
        this.frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addView(View view) {
        this.views.add(view);
        //TODO
    }

    public void activate() {
        frame.setVisible(true);
    }

    public String input(String prompt, List<String> options) {
        //TODO
        return null;
    }
}

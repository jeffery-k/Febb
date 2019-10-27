package febb.display;

import javax.swing.*;
import java.util.List;

public class Display {
    private static final String MAIN_VIEW_NAME = "Main";

    private String name;
    private View defaultView;
    private View currentView;
    private InputPanel inputPanel;
    private JFrame frame;
    public Display(String name) {
        this.name = name;
        this.defaultView = new View();
        this.currentView = defaultView;

        this.frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputPanel = new InputPanel(frame);
        //TODO
    }

    public void setCurrentView(View view) {
        currentView.dump();
        view.load();
        view.update();
        this.currentView = view;
    }

    public void activate() {
        frame.setVisible(true);
    }

    public void clearView() {
        setCurrentView(defaultView);
    }

    public String buttonInput(String prompt, List<String> options) {
        inputPanel.setPrompt(prompt);
        inputPanel.setButtonOptions(options);
        inputPanel.show();
        String input = inputPanel.input();
        inputPanel.hide();
        return input;
    }

    public String selectionInput(String prompt, List<String> options) {
        inputPanel.setPrompt(prompt);
        inputPanel.setSelectionOptions(options);
        inputPanel.show();
        String input = inputPanel.input();
        inputPanel.hide();
        return input;
    }

    public boolean isActive() {
        return frame.isActive();
    }
}

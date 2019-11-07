package febb.display;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Display extends JPanel {
    private static final String MAIN_VIEW_NAME = "Main";

    private String name;
    private View defaultView;
    private View currentView;
    private ButtonInputPanel buttonInputPanel;
    private SelectionInputPanel selectionInputPanel;
    private JFrame frame;
    public Display(String name) {
        super(new BorderLayout());
        this.name = name;
        this.defaultView = new View();
        this.currentView = defaultView;

        this.buttonInputPanel = new ButtonInputPanel(this);
        this.selectionInputPanel = new SelectionInputPanel(this);
        this.buttonInputPanel.setVisible(false);
        this.selectionInputPanel.setVisible(false);
        this.add(buttonInputPanel, BorderLayout.SOUTH);
        this.add(selectionInputPanel, BorderLayout.SOUTH);

        this.frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setCurrentView(View view) {
        this.currentView.dump();
        this.currentView = view;
        this.currentView.load();
    }

    public void activate() {
        frame.setVisible(true);
    }

    public void clearView() {
        setCurrentView(defaultView);
    }

    public String buttonInput(String prompt, List<String> options) {
        return getInput(prompt, options, buttonInputPanel);
    }

    public String selectionInput(String prompt, List<String> options) {
        return getInput(prompt, options, selectionInputPanel);
    }

    private String getInput(String prompt, List<String> options, InputPanel inputPanel) {
        inputPanel.setPrompt(prompt);
        inputPanel.setOptions(options);
        inputPanel.setVisible(true);
        String input = inputPanel.input();
        inputPanel.setVisible(false);
        return input;
    }

    public boolean isActive() {
        return frame.isActive();
    }
}

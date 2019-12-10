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
        this.currentView = null;

        this.buttonInputPanel = new ButtonInputPanel(this);
        this.selectionInputPanel = new SelectionInputPanel(this);
        this.buttonInputPanel.setVisible(false);
        this.selectionInputPanel.setVisible(false);

        this.frame = new JFrame(name);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setContentPane(this);
        this.setCurrentView(defaultView);
    }

    public void setCurrentView(View view) {
        if (currentView != null) {
            this.remove(currentView);
        }
        this.currentView = view;
        if (currentView != null) {
            this.add(currentView, BorderLayout.NORTH);
        }
        this.frame.pack();
    }

    public void activate() {
        this.setVisible(true);
        frame.setVisible(true);
        this.frame.pack();
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

        this.add(inputPanel, BorderLayout.PAGE_END);
        inputPanel.setVisible(true);
        this.frame.pack();

        String input = inputPanel.input();

        inputPanel.setVisible(false);
        this.remove(inputPanel);

        return input;
    }

    public boolean isActive() {
        return frame.isActive();
    }
}

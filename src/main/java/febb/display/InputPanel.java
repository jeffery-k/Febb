package febb.display;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class InputPanel extends JPanel {
    private static final int DEFAULT_HEIGHT = 100;
    private static final int DEFAULT_WIDTH = 100;
    private ValueDungeon<String> value;
    private JTextArea prompt;
    private Display display;

    public InputPanel(Display display) {
        super(new BorderLayout());
        this.value = new ValueDungeon<>();
        this.prompt = new JTextArea();
        this.prompt.setEditable(false);
        this.add(prompt, BorderLayout.NORTH);
        this.display = display;
    }

    public void setPrompt(String prompt) {
        this.prompt.setText(prompt);
    }

    public abstract void setOptions(List<String> options);

    public String input() {
        String input = value.harvestValue();
        return input;
    }

    protected void returnString(String string) {
        value.loadValue(string);
    }
}

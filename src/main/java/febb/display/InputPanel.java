package febb.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InputPanel extends JPanel implements ActionListener {
    private JTextArea prompt;
    private JPanel buttonArea;
    private Display display;
    private ValueDungeon<String> value;

    public InputPanel(Display display) {
        this.prompt = new JTextArea();
        this.prompt.setEditable(false);
        this.add(prompt, BorderLayout.NORTH);
        this.buttonArea = new JPanel();
        this.display = display;
        this.value = new ValueDungeon<String>();
    }

    public void setPrompt(String prompt) {
        this.prompt.setText(prompt);
    }

    public void setButtonOptions(List<String> options) {
        this.buttonArea.removeAll();
        for (String option : options) {
            JButton button = new JButton(option);
            button.setVerticalTextPosition(AbstractButton.CENTER);
            button.setHorizontalTextPosition(AbstractButton.CENTER);
            button.setActionCommand(option);
            this.buttonArea.add(button);
        }
    }

    public void setSelectionOptions(List<String> options) {
        this.buttonArea.removeAll();
        for (String option : options) {

        }
        //TODO
    }

    public String input() {
        String input = value.harvestValue();
        return input;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        value.loadValue(action);
    }
}

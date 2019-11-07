package febb.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ButtonInputPanel extends InputPanel implements ActionListener {
    private JPanel buttonArea;

    public ButtonInputPanel(Display display) {
        super(display);
        this.buttonArea = new JPanel();
        this.add(buttonArea, BorderLayout.SOUTH);
    }

    public void setOptions(List<String> options) {
        this.buttonArea.removeAll();
        for (String option : options) {
            JButton button = new JButton(option);
            button.setVerticalTextPosition(AbstractButton.CENTER);
            button.setHorizontalTextPosition(AbstractButton.CENTER);
            button.setActionCommand(option);
            button.addActionListener(this);
            this.buttonArea.add(button);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        returnString(action);
    }
}

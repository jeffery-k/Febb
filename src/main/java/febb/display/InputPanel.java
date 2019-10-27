package febb.display;

import javax.swing.*;
import java.util.List;

public class InputPanel {
    private String prompt;
    private JFrame frame;

    public InputPanel(JFrame frame) {
        this.prompt = "";
        this.frame = frame;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setButtonOptions(List<String> options) {
        //TODO
    }

    public void setSelectionOptions(List<String> options) {
        //TODO
    }

    public String input() {
        //TODO
        return null;
    }

    public void show() {
        //TODO
    }

    public void hide() {
        //TODO
    }
}

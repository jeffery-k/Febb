package febb.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SelectionInputPanel extends InputPanel implements ActionListener {
    private static final int VISIBLE_SELECTIONS_COUNT = 5;
    private static final String CONTINUE_BUTTON_STRING = "Continue";

    private DefaultListModel<String> optionsListModel;
    private JList<String> optionsList;
    private JPanel buttonArea;
    private JPanel optionListArea;

    public SelectionInputPanel(Display display) {
        super(display);
        this.buttonArea = new JPanel();
        JButton continueButton = new JButton(CONTINUE_BUTTON_STRING);
        continueButton.setVerticalTextPosition(AbstractButton.CENTER);
        continueButton.setHorizontalTextPosition(AbstractButton.CENTER);
        continueButton.addActionListener(this);
        this.buttonArea.add(continueButton);
        this.add(buttonArea, BorderLayout.SOUTH);


        this.optionListArea = new JPanel();
        this.add(optionListArea, BorderLayout.CENTER);
    }

    public void setOptions(List<String> options) {
        this.optionListArea.removeAll();

        this.optionsListModel = new DefaultListModel<>();
        for (String option : options) {
            this.optionsListModel.addElement(option);
        }
        this.optionsList = new JList<>(optionsListModel);
        this.optionsList.setVisibleRowCount(VISIBLE_SELECTIONS_COUNT);
        this.optionsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.optionsList.setSelectedIndex(0);
        JScrollPane optionsScrollPane = new JScrollPane(optionsList);
        this.optionListArea.add(optionsScrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int selectedIndex = optionsList.getSelectedIndex();
        String selection = optionsListModel.getElementAt(selectedIndex);
        returnString(selection);
    }
}

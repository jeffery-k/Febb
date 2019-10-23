package febb;

import febb.display.Display;
import febb.display.View;

public class Febb {
    private final static String DISPLAY_NAME = "Febb";
    private final static String STRATEGY_VIEW_NAME = "Strategy View";
    private final static String MATCH_VIEW_NAME = "Match View";

    private final static int STRATEGY_VIEW_PANEL_COUNT = 2;
    private final static int MATCH_VIEW_PANEL_COUNT = 3;

    public static void main(String[] args) {
        Display display = new Display(DISPLAY_NAME);
        View strategyView = new View(STRATEGY_VIEW_NAME, STRATEGY_VIEW_PANEL_COUNT);
        View matchView = new View(MATCH_VIEW_NAME, MATCH_VIEW_PANEL_COUNT);

        display.addView(strategyView);
        display.addView(matchView);

        display.activate();
    }
}

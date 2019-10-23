package febb.game;

import febb.display.Display;
import febb.display.View;
import febb.properties.GameProperties;

public class SlideShowGame extends Game {
    private final static String MATCH_VIEW_NAME = "Match View";
    private final static int MATCH_VIEW_PANEL_COUNT = 3;

    private View view;
    public SlideShowGame(GameProperties properties, Display display) {
        super(properties);
        view = new View(MATCH_VIEW_NAME, MATCH_VIEW_PANEL_COUNT);
        display.addView(view);
    }
}

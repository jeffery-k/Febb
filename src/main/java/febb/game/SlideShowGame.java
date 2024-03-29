package febb.game;

import febb.display.SlideShow;
import febb.display.View;
import febb.properties.prototyped.GameConfig;
import febb.strategy.StrategySet;

public class SlideShowGame extends Game {
    private static final String MATCH_VIEW_NAME = "Match View";
    private static final int MATCH_VIEW_PANEL_COUNT = 3;

    private View view;

    public SlideShowGame(GameConfig properties) {
        super(properties);
        view = new View(MATCH_VIEW_NAME, MATCH_VIEW_PANEL_COUNT);
    }

    public SlideShow slideShowSimulate(StrategySet strategySet) {
        //TODO
        return null;
    }
}

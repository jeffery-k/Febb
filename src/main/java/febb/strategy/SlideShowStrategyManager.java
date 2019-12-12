package febb.strategy;

import febb.display.SlideShow;
import febb.display.View;
import febb.properties.BaseConfig;

public class SlideShowStrategyManager extends StrategyManager {
    private static final String STRATEGY_VIEW_NAME = "Strategy View";
    private static final int STRATEGY_VIEW_PANEL_COUNT = 2;

    private View view;

    public SlideShowStrategyManager(BaseConfig properties, String gameName) {
        super(properties, gameName);
        this.view = new View(STRATEGY_VIEW_NAME, STRATEGY_VIEW_PANEL_COUNT);
    }

    public SlideShow slideShowOptimize(String strategyName) {
        highlightStrategy(strategyName);
        //TODO
        unhighlightStrategy(strategyName);
        return null;
    }

    private void highlightStrategy(String strategyName) {
        //TODO
    }

    private void unhighlightStrategy(String strategyName) {
        //TODO
    }

    public String getDisplayInfo() {
        //TODO
        return null;
    }
}

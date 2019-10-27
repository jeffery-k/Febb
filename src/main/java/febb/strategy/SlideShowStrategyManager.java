package febb.strategy;

import febb.display.Display;
import febb.display.SlideShow;
import febb.display.View;
import febb.properties.SimulationProperties;

public class SlideShowStrategyManager extends StrategyManager {
    private final static String STRATEGY_VIEW_NAME = "Strategy View";
    private final static int STRATEGY_VIEW_PANEL_COUNT = 2;

    private View view;
    public SlideShowStrategyManager (SimulationProperties properties, String gameName, Display display) {
        super(properties, gameName);
        this.view = new View(STRATEGY_VIEW_NAME, STRATEGY_VIEW_PANEL_COUNT);
        display.setCurrentView(view);
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

    public void displayInfo() {
        //TODO
    }
}

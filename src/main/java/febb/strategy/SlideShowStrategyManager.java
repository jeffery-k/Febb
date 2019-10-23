package febb.strategy;

import febb.display.Display;
import febb.display.View;
import febb.properties.SimulationProperties;

public class SlideShowStrategyManager extends StrategyManager {
    private final static String STRATEGY_VIEW_NAME = "Strategy View";
    private final static int STRATEGY_VIEW_PANEL_COUNT = 2;

    private View view;
    public SlideShowStrategyManager (SimulationProperties properties, Display display) {
        super(properties);
        this.view = new View(STRATEGY_VIEW_NAME, STRATEGY_VIEW_PANEL_COUNT);
        display.addView(view);
    }
}

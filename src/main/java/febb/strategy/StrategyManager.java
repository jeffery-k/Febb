package febb.strategy;

import febb.properties.SimulationConfig;

import java.util.List;

public class StrategyManager {
    private SimulationConfig properties;
    private StrategySet bestStrategies;
    public StrategyManager(SimulationConfig properties, String gameName) {
        this.properties = properties;
    }

    public List<String> getStrategyNames() {
        //TODO
        return null;
    }

    public StrategySet getBestStrategies() {
        return bestStrategies;
    }
}

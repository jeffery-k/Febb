package febb.strategy;

import febb.properties.SimulationProperties;

import java.util.List;

public class StrategyManager {
    private SimulationProperties properties;
    private StrategySet bestStrategies;
    public StrategyManager(SimulationProperties properties, String gameName) {
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

package febb.strategy;

import febb.properties.BaseConfig;

import java.util.List;

public class StrategyManager {
    private BaseConfig properties;
    private StrategySet bestStrategies;
    public StrategyManager(BaseConfig properties, String gameName) {
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

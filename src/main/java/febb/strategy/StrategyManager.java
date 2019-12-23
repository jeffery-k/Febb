package febb.strategy;

import febb.properties.BaseConfig;

import java.util.List;

public class StrategyManager {
    private BaseConfig properties;
    private StrategySet bestStrategies;

    public StrategyManager(BaseConfig properties, String gameName) {
        this.properties = properties;
        //TODO
    }

    public List<String> getStrategyNames() {
        return bestStrategies.getStrategyKeys();
    }

    public StrategySet getBestStrategies() {
        return bestStrategies;
    }
}

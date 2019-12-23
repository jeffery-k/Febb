package febb.strategy;

import febb.game.Agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrategySet {
    private Map<String, Strategy> strategyMap;
    private List<String> strategyKeys;

    public StrategySet() {
        this.strategyMap = new HashMap<>();
    }

    public Strategy get(String strategyName) {
        return new Strategy(strategyMap.get(strategyName));
    }

    public Strategy get(Agent agent) {
        String alignment = agent.getAlignment();
        Strategy strategy = strategyMap.get(alignment);
        if (strategy == null) {
            strategy = new Strategy(agent);
            this.strategyMap.put(alignment, strategy);
            this.strategyKeys.add(alignment);
        }
        return strategy;
    }

    public void put(String strategyName, Strategy strategy) {
        this.strategyMap.put(strategyName, strategy);
        if (strategyKeys.contains(strategyName)) {
            this.strategyKeys.add(strategyName);
        }
    }

    public List<String> getStrategyKeys() {
        return new ArrayList<>(strategyKeys);
    }
}

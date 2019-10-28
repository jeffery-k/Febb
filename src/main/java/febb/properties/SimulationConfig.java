package febb.properties;

import febb.properties.json.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimulationConfig {
    private Map<String, GameConfig> gameProperties;

    public SimulationConfig(Node config) {
        //TODO
    }

    public List<String> getGamePropertyKeys() {
        List<String> keys =  new ArrayList<String>(gameProperties.keySet());
        return keys;
    }

    public GameConfig getGameProperties(String string) {
        return gameProperties.get(string);
    }
}

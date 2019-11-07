package febb.properties;

import febb.properties.json.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationConfig {
    private static final String STRATEGY_KEY = "strategy";
    private static final String GAMES_KEY = "games";
    private static final String CHARACTERS_KEY = "characters";
    private static final String SKILLS_KEY = "skills";
    private static final String ABILITIES_KEY = "abilities";
    private static final String METRICS_KEY = "metrics";

    private StrategyConfig strategyConfig;
    private Map<String, GameConfig> gameConfigMap;
    private Map<String, CharacterConfig> characterConfigMap;
    private Map<String, SkillConfig> skillConfigMap;
    private Map<String, AbilityConfig> abilityConfigMap;
    private Map<String, MetricConfig> metricConfigMap;

    public SimulationConfig() {
        initMaps();
        this.strategyConfig = new StrategyConfig();
    }

    public SimulationConfig(Node config) {
        initMaps();

        Node strategyNode = config.get(STRATEGY_KEY);
        this.strategyConfig = new StrategyConfig(strategyNode);

        Node gameNodeMap = config.get(GAMES_KEY);
        for (String key : gameNodeMap.getKeys()) {
            Node gameNode = gameNodeMap.get(key);
            GameConfig gameConfig = new GameConfig(gameNode);
            this.gameConfigMap.put(key, gameConfig);
        }

        Node characterNodeMap = config.get(CHARACTERS_KEY);
        for (String key : gameNodeMap.getKeys()) {
            Node characterNode = characterNodeMap.get(key);
            CharacterConfig characterConfig = new CharacterConfig(characterNode);
            this.characterConfigMap.put(key, characterConfig);
        }

        Node skillNodeMap = config.get(SKILLS_KEY);
        for (String key : characterNodeMap.getKeys()) {
            Node skillNode = skillNodeMap.get(key);
            SkillConfig skillConfig = new SkillConfig(skillNode);
            this.skillConfigMap.put(key, skillConfig);
        }

        Node abilityNodeMap = config.get(ABILITIES_KEY);
        for (String key : abilityNodeMap.getKeys()) {
            Node abilityNode = abilityNodeMap.get(key);
            AbilityConfig abilityConfig = new AbilityConfig(abilityNode);
            this.abilityConfigMap.put(key, abilityConfig);
        }

        Node metricsNodeMap = config.get(METRICS_KEY);
        for (String key : metricsNodeMap.getKeys()) {
            Node metricNode = metricsNodeMap.get(key);
            MetricConfig metricConfig = new MetricConfig(metricNode);
            this.metricConfigMap.put(key, metricConfig);
        }

        updatePrototypes(this.gameConfigMap);
        updatePrototypes(this.characterConfigMap);
        updatePrototypes(this.skillConfigMap);
        updatePrototypes(this.abilityConfigMap);
        updatePrototypes(this.metricConfigMap);
    }

    private void initMaps() {
        this.gameConfigMap = new HashMap<String, GameConfig>();
        this.characterConfigMap = new HashMap<String, CharacterConfig>();
        this.skillConfigMap = new HashMap<String, SkillConfig>();
        this.abilityConfigMap = new HashMap<String, AbilityConfig>();
        this.metricConfigMap = new HashMap<String, MetricConfig>();
    }

    private <T extends PrototypedConfig> void updatePrototypes(Map<String, T> configMap) {
        for (PrototypedConfig config : configMap.values()) {
            List<String> prototypes = config.getPrototypes();
            for (String prototypeName : prototypes) {
                //TODO
            }
        }

        //TODO
    }

    public void merge(SimulationConfig simulationConfig) {
        StrategyConfig strategyConfig = simulationConfig.getStrategyConfig();
        this.strategyConfig.merge(strategyConfig);
        for (Map.Entry<String, GameConfig> gameEntry : simulationConfig.gameConfigMap.entrySet()) {
            String key = gameEntry.getKey();
            gameConfigMap.computeIfAbsent(key, k -> gameEntry.getValue());
        }
        for (Map.Entry<String, CharacterConfig> characterEntry : simulationConfig.characterConfigMap.entrySet()) {
            String key = characterEntry.getKey();
            characterConfigMap.computeIfAbsent(key, k -> characterEntry.getValue());
        }
        for (Map.Entry<String, SkillConfig> skillEntry : simulationConfig.skillConfigMap.entrySet()) {
            String key = skillEntry.getKey();
            skillConfigMap.computeIfAbsent(key, k -> skillEntry.getValue());
        }
        for (Map.Entry<String, AbilityConfig> abilityEntry : simulationConfig.abilityConfigMap.entrySet()) {
            String key = abilityEntry.getKey();
            abilityConfigMap.computeIfAbsent(key, k -> abilityEntry.getValue());
        }
        for (Map.Entry<String, MetricConfig> metricEntry : simulationConfig.metricConfigMap.entrySet()) {
            String key = metricEntry.getKey();
            metricConfigMap.computeIfAbsent(key, k -> metricEntry.getValue());
        }
    }

    public List<String> getGamePropertyKeys() {
        return new ArrayList<String>(gameConfigMap.keySet());
    }

    public GameConfig getGameProperties(String string) {
        return gameConfigMap.get(string);
    }
    public List<String> getCharacterPropertyKeys() {
        return new ArrayList<String>(characterConfigMap.keySet());
    }

    public CharacterConfig getCharacterProperties(String string) {
        return characterConfigMap.get(string);
    }

    public List<String> getSkillPropertiesKeys() {
        return new ArrayList<String>(skillConfigMap.keySet());
    }

    public SkillConfig getSkillProperties(String string) {
        return skillConfigMap.get(string);
    }

    public List<String> getAbilityPropertiesKeys() {
        return new ArrayList<String>(abilityConfigMap.keySet());
    }

    public AbilityConfig getAbilityProperties(String string) {
        return abilityConfigMap.get(string);
    }

    public List<String> getMetricPropertiesKeys() {
        return new ArrayList<String>(metricConfigMap.keySet());
    }

    public MetricConfig getMetricProperties(String string) {
        return metricConfigMap.get(string);
    }

    public StrategyConfig getStrategyConfig() {
        return strategyConfig;
    }
}

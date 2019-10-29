package febb.properties;

import febb.properties.json.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationConfig {
    private final static String STRATEGY_KEY = "strategy";
    private final static String GAMES_KEY = "games";
    private final static String CHARACTERS_KEY = "characters";
    private final static String SKILLS_KEY = "skills";
    private final static String ABILITIES_KEY = "abilities";
    private final static String METRICS_KEY = "metrics";

    private StrategyConfig strategyConfig;
    private Map<String, GameConfig> gameConfigMap;
    private Map<String, CharacterConfig> characterConfigMap;
    private Map<String, SkillConfig> skillConfigMap;
    private Map<String, AbilityConfig> abilityConfigMap;
    private Map<String, MetricConfig> metricConfigMap;
    public SimulationConfig(Node config) {
        Node strategyNode = config.get(STRATEGY_KEY);
        this.strategyConfig = new StrategyConfig(strategyNode);
        this.gameConfigMap = new HashMap<String, GameConfig>();
        this.characterConfigMap = new HashMap<String, CharacterConfig>();
        this.skillConfigMap = new HashMap<String, SkillConfig>();
        this.abilityConfigMap = new HashMap<String, AbilityConfig>();
        this.metricConfigMap = new HashMap<String, MetricConfig>();

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

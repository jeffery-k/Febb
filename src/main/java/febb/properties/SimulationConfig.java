package febb.properties;

import febb.properties.json.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationConfig {
    private static final String STRATEGY_KEY = "strategy";
    private static final String GAMES_KEY = "games";
    private static final String AGENTS_KEY = "agents";
    private static final String SKILLS_KEY = "skills";
    private static final String ABILITIES_KEY = "abilities";
    private static final String METRICS_KEY = "metrics";

    private StrategyConfig strategyConfig;
    private Map<String, GameConfig> gameConfigMap;
    private Map<String, AgentConfig> agentConfigMap;
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
        for (String gameKey : gameNodeMap.getKeys()) {
            Node gameNode = gameNodeMap.get(gameKey);
            GameConfig gameConfig = new GameConfig(gameNode);
            this.gameConfigMap.put(gameKey, gameConfig);
        }

        Node agentNodeMap = config.get(AGENTS_KEY);
        for (String agentKey : agentNodeMap.getKeys()) {
            Node agentNode = agentNodeMap.get(agentKey);
            AgentConfig agentConfig = new AgentConfig(agentNode);
            this.agentConfigMap.put(agentKey, agentConfig);
        }

        Node skillNodeMap = config.get(SKILLS_KEY);
        for (String skillKey : skillNodeMap.getKeys()) {
            Node skillNode = skillNodeMap.get(skillKey);
            SkillConfig skillConfig = new SkillConfig(skillNode);
            this.skillConfigMap.put(skillKey, skillConfig);
        }

        Node abilityNodeMap = config.get(ABILITIES_KEY);
        for (String abilityKey : abilityNodeMap.getKeys()) {
            Node abilityNode = abilityNodeMap.get(abilityKey);
            AbilityConfig abilityConfig = new AbilityConfig(abilityNode);
            this.abilityConfigMap.put(abilityKey, abilityConfig);
        }

        Node metricsNodeMap = config.get(METRICS_KEY);
        for (String metricsKey : metricsNodeMap.getKeys()) {
            Node metricNode = metricsNodeMap.get(metricsKey);
            MetricConfig metricConfig = new MetricConfig(metricNode);
            this.metricConfigMap.put(metricsKey, metricConfig);
        }
    }

    private void initMaps() {
        this.gameConfigMap = new HashMap<String, GameConfig>();
        this.agentConfigMap = new HashMap<String, AgentConfig>();
        this.skillConfigMap = new HashMap<String, SkillConfig>();
        this.abilityConfigMap = new HashMap<String, AbilityConfig>();
        this.metricConfigMap = new HashMap<String, MetricConfig>();
    }

    public void merge(SimulationConfig simulationConfig) {
        StrategyConfig strategyConfig = simulationConfig.strategyConfig;
        this.strategyConfig.merge(strategyConfig);
        for (Map.Entry<String, GameConfig> gameEntry : simulationConfig.gameConfigMap.entrySet()) {
            String key = gameEntry.getKey();
            gameConfigMap.computeIfAbsent(key, k -> gameEntry.getValue());
        }
        for (Map.Entry<String, AgentConfig> agentEntry : simulationConfig.agentConfigMap.entrySet()) {
            String key = agentEntry.getKey();
            agentConfigMap.computeIfAbsent(key, k -> agentEntry.getValue());
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

    public List<String> getAgentPropertyKeys() {
        return new ArrayList<String>(agentConfigMap.keySet());
    }

    public AgentConfig getAgentProperties(String string) {
        return agentConfigMap.get(string);
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

    public Map<String, GameConfig> getGameConfigMap() {
        return gameConfigMap;
    }

    public Map<String, AgentConfig> getAgentConfigMap() {
        return agentConfigMap;
    }

    public Map<String, SkillConfig> getSkillConfigMap() {
        return skillConfigMap;
    }

    public Map<String, AbilityConfig> getAbilityConfigMap() {
        return abilityConfigMap;
    }

    public Map<String, MetricConfig> getMetricConfigMap() {
        return metricConfigMap;
    }
}

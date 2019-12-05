package febb.properties;

import febb.properties.json.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseConfig {
    private static final String STRATEGY_KEY = "strategy";
    private static final String GAMES_KEY = "games";
    private static final String AGENTS_KEY = "agents";
    private static final String SKILLS_KEY = "skills";
    private static final String ABILITIES_KEY = "abilities";
    private static final String METRICS_KEY = "metrics";
    private static final String PROTOTYPES_KEY = "prototypes";

    private StrategyConfig strategyConfig;
    private Map<String, PrototypedConfig> gameConfigMap;
    private Map<String, PrototypedConfig> agentConfigMap;
    private Map<String, PrototypedConfig> skillConfigMap;
    private Map<String, PrototypedConfig> abilityConfigMap;
    private Map<String, PrototypedConfig> metricConfigMap;

    public BaseConfig() {
        this.gameConfigMap = new HashMap<String, PrototypedConfig>();
        this.agentConfigMap = new HashMap<String, PrototypedConfig>();
        this.skillConfigMap = new HashMap<String, PrototypedConfig>();
        this.abilityConfigMap = new HashMap<String, PrototypedConfig>();
        this.metricConfigMap = new HashMap<String, PrototypedConfig>();
        this.strategyConfig = new StrategyConfig();
    }

    public BaseConfig(Node config) {
        this.gameConfigMap = new HashMap<String, PrototypedConfig>();
        this.agentConfigMap = new HashMap<String, PrototypedConfig>();
        this.skillConfigMap = new HashMap<String, PrototypedConfig>();
        this.abilityConfigMap = new HashMap<String, PrototypedConfig>();
        this.metricConfigMap = new HashMap<String, PrototypedConfig>();

        Node strategyNode = config.get(STRATEGY_KEY);
        this.strategyConfig = new StrategyConfig(strategyNode);

        Node gameNodeMap = config.get(GAMES_KEY);
        //TODO: use initConfig method instead
        for (String gameKey : gameNodeMap.getKeys()) {
            Node gameNode = gameNodeMap.get(gameKey);
            GameConfig gameConfig = new GameConfig(gameNode);

            Node prototypeNames = gameNode.get(PROTOTYPES_KEY);
            for (int i = 0; i < prototypeNames.size(); i++) {
                Node prototype = gameNodeMap.get(prototypeNames.getStringValue());
                gameConfig.implement(prototype);
            }
            gameConfig.init();
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

    private void initConfig(PrototypedConfig config, Map<String, PrototypedConfig> prototypes) {
        List<String> prototypeNames = config.getPrototypes();
        for (String prototypeName : prototypeNames) {
            PrototypedConfig prototype = prototypes.get(prototypeName);
            config.implement(prototype);
        }
        config.init();
    }

    public void merge(BaseConfig baseConfig) {
        StrategyConfig strategyConfig = baseConfig.strategyConfig;
        this.strategyConfig.merge(strategyConfig);
        merge(this.gameConfigMap, baseConfig.gameConfigMap);
        merge(this.agentConfigMap, baseConfig.agentConfigMap);
        merge(this.skillConfigMap, baseConfig.skillConfigMap);
        merge(this.abilityConfigMap, baseConfig.abilityConfigMap);
        merge(this.metricConfigMap, baseConfig.metricConfigMap);
    }

    private static <K, V> void merge(Map<K, V> configMap, Map<K, V> newConfigMap) {
        for (Map.Entry<K, V> entry : newConfigMap.entrySet()) {
            K key = entry.getKey();
            configMap.computeIfAbsent(key, k -> entry.getValue());
        }
    }

    private List<String> getConcreteKeys(Map<String, PrototypedConfig> map) {
        List<String> concreteKeys = new ArrayList<String>();
        for (Map.Entry<String, PrototypedConfig> entry : map.entrySet()) {
            if (entry.getValue().isPrototype()) {
                concreteKeys.add(entry.getKey());
            }
        }
        return concreteKeys;
    }

    public List<String> getConcreteGamePropertyKeys() {
        return getConcreteKeys(gameConfigMap);
    }

    public GameConfig getGameProperties(String string) {
        return (GameConfig) gameConfigMap.get(string);
    }

    public List<String> getConcreteAgentPropertyKeys() {
        return getConcreteKeys(agentConfigMap);
    }

    public AgentConfig getAgentProperties(String string) {
        return (AgentConfig) agentConfigMap.get(string);
    }

    public List<String> getConcreteSkillPropertiesKeys() {
        return getConcreteKeys(skillConfigMap);
    }

    public SkillConfig getSkillProperties(String string) {
        return (SkillConfig) skillConfigMap.get(string);
    }

    public List<String> getConcreteAbilityPropertiesKeys() {
        return getConcreteKeys(abilityConfigMap);
    }

    public AbilityConfig getAbilityProperties(String string) {
        return (AbilityConfig) abilityConfigMap.get(string);
    }

    public List<String> getConcreteMetricPropertiesKeys() {
        return getConcreteKeys(metricConfigMap);
    }

    public MetricConfig getMetricProperties(String string) {
        return (MetricConfig) metricConfigMap.get(string);
    }
}

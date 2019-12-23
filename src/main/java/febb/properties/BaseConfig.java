package febb.properties;

import febb.properties.exception.MissingPrototypeException;
import febb.properties.json.Node;
import febb.properties.json.NodeType;
import febb.properties.prototyped.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BaseConfig {
    private static final String STRATEGY_KEY = "strategy";
    private static final String GAMES_KEY = "games";
    private static final String AGENTS_KEY = "agents";
    private static final String SKILLS_KEY = "skills";
    private static final String ABILITIES_KEY = "abilities";
    private static final String METRICS_KEY = "metrics";

    private StrategyConfig strategyConfig;
    private Map<String, PrototypedConfig> gameConfigMap;
    private Map<String, PrototypedConfig> agentConfigMap;
    private Map<String, PrototypedConfig> skillConfigMap;
    private Map<String, PrototypedConfig> abilityConfigMap;
    private Map<String, PrototypedConfig> metricConfigMap;

    public BaseConfig() {
        this.gameConfigMap = new HashMap<>();
        this.agentConfigMap = new HashMap<>();
        this.skillConfigMap = new HashMap<>();
        this.abilityConfigMap = new HashMap<>();
        this.metricConfigMap = new HashMap<>();
        this.strategyConfig = new StrategyConfig();
    }

    public BaseConfig(Node config) {
        this.gameConfigMap = new HashMap<>();
        this.agentConfigMap = new HashMap<>();
        this.skillConfigMap = new HashMap<>();
        this.abilityConfigMap = new HashMap<>();
        this.metricConfigMap = new HashMap<>();

        Node strategyNode = config.get(STRATEGY_KEY);
        this.strategyConfig = new StrategyConfig(strategyNode);

        Node gameNodeMap = config.get(GAMES_KEY);
        for (String gameKey : gameNodeMap.getKeys()) {
            Node gameNode = gameNodeMap.get(gameKey);
            PrototypedConfig gameConfig;
            if (gameNode.getNodeType() == NodeType.ARRAY) {
                gameConfig = new ConfigList(gameNode);
            } else {
                gameConfig = new GameConfig(gameNode);
                initConfig(gameConfig, this.gameConfigMap);
            }
            this.gameConfigMap.put(gameKey, gameConfig);
        }

        Node agentNodeMap = config.get(AGENTS_KEY);
        for (String agentKey : agentNodeMap.getKeys()) {
            Node agentNode = agentNodeMap.get(agentKey);
            PrototypedConfig agentConfig;
            if (agentNode.getNodeType() == NodeType.ARRAY) {
                agentConfig = new ConfigList(agentNode);
            } else {
                agentConfig = new AgentConfig(agentNode);
                initConfig(agentConfig, this.abilityConfigMap);
            }
            this.agentConfigMap.put(agentKey, agentConfig);
        }

        Node skillNodeMap = config.get(SKILLS_KEY);
        for (String skillKey : skillNodeMap.getKeys()) {
            Node skillNode = skillNodeMap.get(skillKey);
            PrototypedConfig skillConfig;
            if (skillNode.getNodeType() == NodeType.ARRAY) {
                skillConfig = new ConfigList(skillNode);
            } else {
                skillConfig = new SkillConfig(skillNode);
                initConfig(skillConfig, this.skillConfigMap);
            }
            this.skillConfigMap.put(skillKey, skillConfig);
        }

        Node abilityNodeMap = config.get(ABILITIES_KEY);
        for (String abilityKey : abilityNodeMap.getKeys()) {
            Node abilityNode = abilityNodeMap.get(abilityKey);
            PrototypedConfig abilityConfig;
            if (abilityNode.getNodeType() == NodeType.ARRAY) {
                abilityConfig = new ConfigList(abilityNode);
            } else {
                abilityConfig = new AbilityConfig(abilityNode);
                initConfig(abilityConfig, this.abilityConfigMap);
            }
            this.abilityConfigMap.put(abilityKey, abilityConfig);
        }

        Node metricsNodeMap = config.get(METRICS_KEY);
        for (String metricsKey : metricsNodeMap.getKeys()) {
            Node metricNode = metricsNodeMap.get(metricsKey);
            PrototypedConfig metricConfig;
            if (metricNode.getNodeType() == NodeType.ARRAY) {
                metricConfig = new ConfigList(metricNode);
            } else {
                metricConfig = new MetricConfig(metricNode);
                initConfig(metricConfig, this.metricConfigMap);
            }
            this.metricConfigMap.put(metricsKey, metricConfig);
        }
    }

    private void initConfig(PrototypedConfig config, Map<String, PrototypedConfig> prototypes) {
        List<String> prototypeNames = config.getPrototypes();
        for (String prototypeName : prototypeNames) {
            PrototypedConfig prototype = prototypes.get(prototypeName);
            if (prototype == null) {
                throw new MissingPrototypeException(prototypeName);
            }
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
        List<String> concreteKeys = new ArrayList<>();
        for (Map.Entry<String, PrototypedConfig> entry : map.entrySet()) {
            if (!entry.getValue().isPrototype()) {
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

    public List<AgentConfig> getAgentProperties(String string) {
        List<PrototypedConfig> configs = collapseConfig(string, agentConfigMap);
        return configs.stream().map(config -> (AgentConfig) config).collect(Collectors.toList());
    }

    public List<String> getConcreteSkillPropertiesKeys() {
        return getConcreteKeys(skillConfigMap);
    }

    public List<SkillConfig> getSkillProperties(String string) {
        List<PrototypedConfig> configs = collapseConfig(string, skillConfigMap);
        return configs.stream().map(config -> (SkillConfig) config).collect(Collectors.toList());
    }

    public List<String> getConcreteAbilityPropertiesKeys() {
        return getConcreteKeys(abilityConfigMap);
    }

    public List<AbilityConfig> getAbilityProperties(String string) {
        List<PrototypedConfig> configs = collapseConfig(string, abilityConfigMap);
        return configs.stream().map(config -> (AbilityConfig) config).collect(Collectors.toList());
    }

    public List<String> getConcreteMetricPropertiesKeys() {
        return getConcreteKeys(metricConfigMap);
    }

    public List<MetricConfig> getMetricProperties(String string) {
        List<PrototypedConfig> configs = collapseConfig(string, metricConfigMap);
        return configs.stream().map(config -> (MetricConfig) config).collect(Collectors.toList());
    }

    private <T extends PrototypedConfig> List<T> collapseConfig(String key, Map<String, T> configMap) {
        List<T> configs = new ArrayList<>();
        T config = configMap.get(key);
        if (config.isList()) {
            List<String> keys = config.getList();
            for (String subKey : keys) {
                configs.addAll(collapseConfig(subKey, configMap));
            }
        } else {
            configs.add(config);
        }
        return configs;
    }
}

package febb.properties;

import febb.properties.json.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgentConfig extends PrototypedConfig {
    private static final String POSITION_KEY = "position";
    private static final String SKILL_NAMES_KEY = "skills";
    private static final String ABILITY_NAMES_KEY = "abilities";
    private static final String METRIC_QUANTITIES_KEY = "metrics";

    private int[] position;
    private List<String> skillNames;
    private List<String> abilityNames;
    private Map<String, Integer> metricQuantities;
    public AgentConfig(Node config) {
        super(config);
        this.position = new int[]{
                config.get(0).getIntegerValue(),
                config.get(1).getIntegerValue()};
        this.skillNames = new ArrayList<String>();
        this.abilityNames = new ArrayList<String>();
        this.metricQuantities = new HashMap<String, Integer>();

        Node skillNamesNode = config.get(SKILL_NAMES_KEY);
        for (int i = 0; i < skillNamesNode.size(); i++) {
            String skillName = skillNamesNode.get(i).getStringValue();
            this.skillNames.add(skillName);
        }
        Node abilityNames = config.get(ABILITY_NAMES_KEY);
        for (int i = 0; i < abilityNames.size(); i++) {
            Node abilityNameNode = abilityNames.get(i);
            this.abilityNames.add(abilityNameNode.getStringValue());
        }
        for (String metricName : config.get(METRIC_QUANTITIES_KEY).getKeys()) {
            this.metricQuantities.put(metricName, config.get(METRIC_QUANTITIES_KEY).get(metricName).getIntegerValue());
        }
        //TODO
    }

    public void init() {
        //TODO
    }
}
package febb.properties;

import febb.properties.json.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgentConfig extends PrototypedConfig<AgentConfig> {
    private static final String POSITION_KEY = "position";
    private static final String SKILLS_KEY = "skills";
    private static final String ABILITIES_KEY = "abilities";
    private static final String METRICS_KEY = "metrics";

    private int[] position;
    private List<String> skills;
    private List<String> abilities;
    private Map<String, Integer> metrics;
    public AgentConfig(Node config) {
        super();
        this.position = new int[]{
                config.get(0).getIntegerValue(),
                config.get(1).getIntegerValue()};
        this.skills = new ArrayList<String>();
        this.abilities = new ArrayList<String>();
        this.metrics = new HashMap<String, Integer>();

        for (Node skillNode : config.get(SKILLS_KEY)) {
            this.skills.add(skillNode.getStringValue());
        }
        for (Node abilityNode : config.get(ABILITIES_KEY)) {
            this.abilities.add(abilityNode.getStringValue());
        }
        for (String metricName : config.get(METRICS_KEY).getKeys()) {
            this.metrics.put(metricName, config.get(METRICS_KEY).get(metricName).getIntegerValue());
        }
        //TODO
    }

    @Override
    public void implement(AgentConfig prototype) {
        //TODO
    }
}

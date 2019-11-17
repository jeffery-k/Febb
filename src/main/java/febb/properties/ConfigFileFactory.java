package febb.properties;

import febb.properties.json.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfigFileFactory {
    private static final String REGEX_EMPTY = "[\t\n ]*";
    private static final String RESOURCES_KEY = "resources";
    private static final String RESOURCE_BASE_DIR = "config/";

    private String baseFile;
    private List<Node> simulationConfigNodes;

    public ConfigFileFactory(String baseFile) throws IOException {
        this.baseFile = baseFile;
        this.simulationConfigNodes = new ArrayList<Node>();

        String fileString = getFileContents(baseFile).replaceAll(REGEX_EMPTY, "");
        this.simulationConfigNodes.add(Node.getNode(fileString));
        for (Node resource : simulationConfigNodes.get(0).get(RESOURCES_KEY)) {
            String resourceName = resource.getStringValue();
            fileString = getFileContents(RESOURCE_BASE_DIR + resourceName).replaceAll(REGEX_EMPTY, "");;
            this.simulationConfigNodes.add(Node.getNode(fileString));
        }
        this.baseFile = baseFile;
        //TODO: Implement prototypes
    }

    private static String getFileContents(String file) throws IOException {
        InputStreamReader input;
        try {
            input = new InputStreamReader(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            return null;
        }
        BufferedReader reader = new BufferedReader(input);
        String line = reader.readLine();
        StringBuilder stringBuilder = new StringBuilder();
        while(line != null) {
            stringBuilder.append(line).append("\n");
            line = reader.readLine();
        }

        return stringBuilder.toString();
    }

    public SimulationConfig getSimulationProperties() {
        SimulationConfig simulationConfig = new SimulationConfig();
        for (Node node : simulationConfigNodes) {
            SimulationConfig config = new SimulationConfig(node);
            simulationConfig.merge(config);
        }
        updatePrototypes(simulationConfig.getGameConfigMap());
        updatePrototypes(simulationConfig.getAgentConfigMap());
        updatePrototypes(simulationConfig.getSkillConfigMap());
        updatePrototypes(simulationConfig.getAbilityConfigMap());
        updatePrototypes(simulationConfig.getMetricConfigMap());
        return simulationConfig;
    }

    private static <T extends PrototypedConfig> void updatePrototypes(Map<String, T> configMap) {
        for (PrototypedConfig config : configMap.values()) {
            List<String> prototypes = config.getPrototypes();
            for (String prototypeName : prototypes) {
                T prototype = configMap.get(prototypeName);
                config.implement(prototype);
            }
        }
    }
}

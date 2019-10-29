package febb.properties;

import febb.properties.json.Node;
import febb.properties.json.ObjectNode;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ConfigFileFactory {
    private static final String RESOURCES_KEY = "resources";

    private String baseFile;
    private List<Node> simulationConfigNodes;

    public ConfigFileFactory(String baseFile) throws IOException {
        String fileString = getFileContents(baseFile);
        this.simulationConfigNodes.add(new ObjectNode(fileString));
        for (Node resource : simulationConfigNodes.get(0).get(RESOURCES_KEY)) {
            String resourceName = resource.getStringValue();
            fileString = getFileContents(resourceName);
            this.simulationConfigNodes.add(new ObjectNode(fileString));
        }
        this.baseFile = baseFile;
    }

    private String getFileContents(String file) throws IOException {
        InputStreamReader input = new InputStreamReader(new FileInputStream(file));
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
        return simulationConfig;
    }
}

package febb.properties;

import febb.properties.json.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        Node resourceNames = simulationConfigNodes.get(0).get(RESOURCES_KEY);
        for (int i = 0; i < resourceNames.size(); i++) {
            String resourceName = resourceNames.get(i).getStringValue();
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

    public BaseConfig getSimulationProperties() {
        BaseConfig baseConfig = new BaseConfig();
        for (Node node : simulationConfigNodes) {
            BaseConfig config = new BaseConfig(node);
            baseConfig.merge(config);
        }
        return baseConfig;
    }
}

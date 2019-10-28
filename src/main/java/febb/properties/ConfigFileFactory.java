package febb.properties;

import febb.properties.json.Node;
import febb.properties.json.ObjectNode;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConfigFileFactory {
    private String directory;
    private Node simulationConfigNode;

    public ConfigFileFactory(String directory) throws IOException {
        this.directory = directory;

        InputStreamReader input = new InputStreamReader(new FileInputStream(directory));
        BufferedReader reader = new BufferedReader(input);
        String line = reader.readLine();
        StringBuilder stringBuilder = new StringBuilder();
        while(line != null) {
            stringBuilder.append(line).append("\n");
            line = reader.readLine();
        }

        String string = stringBuilder.toString();
        this.simulationConfigNode = new ObjectNode(string);
    }

    public SimulationConfig getSimulationProperties() {
        return new SimulationConfig(simulationConfigNode);
    }
}

package febb.properties.json;

import java.util.Map;

public class ObjectNode extends Node {
    private Map<String, Node> mapNode;

    public ObjectNode(String string) {
        this.characters = string.toCharArray();
        this.size = 0;

        while(size < characters.length) {
            String key = nextKey();
            String body = nextBody();
            Node node = getNode(body);
            this.mapNode.put(key, node);
        }
    }

    private String nextKey() {
        char value = characters[size];
        StringBuilder keyBuilder = new StringBuilder();

        while (value != KEY_DELIMITER) {
            keyBuilder.append(value);
            value = nextChar();
        }

        return keyBuilder.toString();
    }
}

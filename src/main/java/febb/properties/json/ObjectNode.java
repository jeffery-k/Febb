package febb.properties.json;

import java.util.HashMap;
import java.util.Map;

class ObjectNode extends Node {
    private Map<String, Node> mapNode;

     ObjectNode(String string) {
        this.characters = string.toCharArray();
        this.characterSize = 0;
        this.mapNode = new HashMap<String, Node>();

        while(characterSize < characters.length) {
            String key = nextKey();
            String body = nextBody();
            Node node = getNode(body);
            this.mapNode.put(key, node);
        }
    }

    private String nextKey() {
        char value = nextChar();
        StringBuilder keyBuilder = new StringBuilder();

        while (value != KEY_DELIMITER) {
            keyBuilder.append(value);
            value = nextChar();
        }

        String charString = keyBuilder.toString();
        return new StringWrapper(charString).getContents();
    }

    @Override
    public Node get(String key) {
        Node value =  mapNode.get(key);
        if (value == null) {
            return new NullNode();
        } else {
            return value;
        }
    }

    @Override
    public Iterable<String> getKeys() {
        return mapNode.keySet();
    }
}

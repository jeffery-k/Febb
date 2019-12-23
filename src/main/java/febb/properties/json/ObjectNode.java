package febb.properties.json;

import febb.properties.exception.UnsupportedMethodException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ObjectNode extends Node {
    private List<String> keys;
    private Map<String, Node> nodeMap;

    ObjectNode(String string) {
        this.characters = string.toCharArray();
        this.characterSize = 0;
        this.keys = new ArrayList<>();
        this.nodeMap = new HashMap<>();
        this.nodeType = NodeType.OBJECT;

        while (characterSize < characters.length) {
            String key = nextKey();
            String body = nextBody();
            Node node = getNode(body);
            this.keys.add(key);
            this.nodeMap.put(key, node);
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
        return get(key, true);
    }

    @Override
    public Node get(String key, boolean nullable) {
        Node value = nodeMap.get(key);
        if (value == null) {
            return new NullNode();
        } else if (nullable) {
            throw new UnsupportedMethodException(this.getClass());
        }else {
            return value;
        }
    }

    @Override
    public Iterable<String> getKeys() {
        return new ArrayList<>(this.keys);
    }
}

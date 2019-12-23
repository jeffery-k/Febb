package febb.properties.json;

import febb.properties.exception.UnsupportedMethodException;

import java.util.ArrayList;
import java.util.List;

class ArrayNode extends Node {
    private List<Node> nodes;

    ArrayNode(String string) {
        this.characters = string.toCharArray();
        this.characterSize = 0;
        this.nodes = new ArrayList<>();
        this.nodeType = NodeType.ARRAY;

        while (characterSize < characters.length) {
            String body = nextBody();
            Node node = getNode(body);
            this.nodes.add(node);
        }
    }

    @Override
    public Node get(int index) {
        return get(index, true);
    }

    @Override
    public Node get(int index, boolean nullable) {
        if (index < nodes.size()) {
            return nodes.get(index);
        } else if (nullable){
            throw new UnsupportedMethodException(this.getClass());
        } else {
            return new NullNode();
        }
    }

    @Override
    public int size() {
        return nodes.size();
    }
}

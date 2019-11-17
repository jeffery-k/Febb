package febb.properties.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ArrayNode extends Node implements Iterable<Node> {
    private List<Node> nodes;

    ArrayNode(String string) {
        this.characters = string.toCharArray();
        this.characterSize = 0;
        this.nodes = new ArrayList<Node>();

        while(characterSize < characters.length) {
            String body = nextBody();
            Node node = getNode(body);
            this.nodes.add(node);
        }
    }

    @Override
    public Node get(int index) {
        if (index < nodes.size()) {
            return nodes.get(index);
        } else {
            return new NullNode();
        }
    }

    @Override
    public Iterator<Node> iterator() {
        return nodes.iterator();
    }
}

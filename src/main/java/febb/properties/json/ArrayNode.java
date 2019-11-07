package febb.properties.json;

import java.util.Iterator;
import java.util.List;

public class ArrayNode extends Node implements Iterable<Node> {
    private List<Node> nodes;

    public ArrayNode(String string) {
        this.characters = string.toCharArray();
        this.size = 0;

        while(size < characters.length) {
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

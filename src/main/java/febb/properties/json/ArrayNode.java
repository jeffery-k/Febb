package febb.properties.json;

import java.util.Iterator;
import java.util.List;

public class ArrayNode extends Node implements Iterable<Node> {
    private List<Node> nodes;

    @Override
    public Iterator<Node> iterator() {
        return nodes.iterator();
    }
}

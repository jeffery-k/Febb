package febb.properties.json;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class NullNode extends Node {
    @Override
    public Node get(String key) {
        return new NullNode();
    }

    @Override
    public Node get(int index) {
        return new NullNode();
    }

    @Override
    public Iterable<String> getKeys() {
        return new ArrayList<String>();
    }

    @Override
    public Iterator<Node> iterator() {
        return Collections.emptyIterator();
    }

    @Override
    public String getStringValue() {
        return "";
    }

    @Override
    public double getDoubleValue() {
        return 0;
    }

    @Override
    public boolean getBooleanValue() {
        return false;
    }

    @Override
    public boolean isNull() {
        return true;
    }
}

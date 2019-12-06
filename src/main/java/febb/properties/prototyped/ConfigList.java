package febb.properties.prototyped;

import febb.properties.exception.UnsupportedMethodException;
import febb.properties.json.Node;

import java.util.List;
import java.util.Map;

public class ConfigList extends PrototypedConfig {
    public ConfigList(Node config) {
        super();
    }

    @Override
    public void init() {
        //TODO
    }

    @Override
    protected void loadProperties() {
        throw new UnsupportedMethodException(this.getClass());
    }

    @Override
    public boolean isPrototype() {
        throw new UnsupportedMethodException(this.getClass());
    }

    @Override
    public Map<String, Node> getProperties() {
        throw new UnsupportedMethodException(this.getClass());
    }

    @Override
    public List<String> getPrototypes() {
        throw new UnsupportedMethodException(this.getClass());
    }

    @Override
    public void implement(PrototypedConfig prototype) {
        throw new UnsupportedMethodException(this.getClass());
    }

    @Override
    public void implement(Node prototype) {
        throw new UnsupportedMethodException(this.getClass());
    }
}

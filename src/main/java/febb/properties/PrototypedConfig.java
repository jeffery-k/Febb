package febb.properties;

import java.util.ArrayList;
import java.util.List;

//TODO: Initiate prototypes in implementing constructors
//TODO: Add null-checks to all node accepting constructors
public abstract class PrototypedConfig <T extends PrototypedConfig> {
    protected String PROTOTYPE_KEY = "prototype";
    protected String PROTOTYPES_KEY = "prototypes";

    protected boolean prototype;
    protected List<String> prototypes;

    public PrototypedConfig() {
        prototype = false;
        prototypes = new ArrayList<String>();
    }

    public List<String> getPrototypes() {
        return new ArrayList<String>(prototypes);
    }

    public abstract void implement(T prototype);
}

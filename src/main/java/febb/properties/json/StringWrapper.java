package febb.properties.json;

import java.util.Arrays;

class StringWrapper {
    private static final String REGEX_BOOLEAN = "(true|false)";

    private NodeType nodeType;
    private String contents;
    StringWrapper(String string) {
        char[] agents = string.toCharArray();
        char startAgent = agents[0];
        char endAgent = agents[agents.length-1];


        if (Node.START_OBJECT == startAgent && Node.END_OBJECT == endAgent) {
            char[] charArray = Arrays.copyOfRange(agents, 1, agents.length-1);
            this.contents = new String(charArray);
            this.nodeType = NodeType.OBJECT;
        } else if (Node.START_ARRAY == startAgent && Node.END_ARRAY == endAgent) {
            char[] charArray = Arrays.copyOfRange(agents, 1, agents.length-1);
            this.contents = new String(charArray);
            this.nodeType = NodeType.ARRAY;
        } else if (Node.START_STRING == startAgent && Node.END_STRING == endAgent) {
            char[] charArray = Arrays.copyOfRange(agents, 1, agents.length-1);
            this.contents = new String(charArray);
            this.nodeType = NodeType.STRING;
        } else if (new String(agents).matches(REGEX_BOOLEAN)) {
            this.contents = new String(agents);
            this.nodeType = NodeType.BOOLEAN;
        } else {
            this.contents = new String(agents);
            this.nodeType = NodeType.NUMBER;
        }
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public String getContents() {
        return contents;
    }
}

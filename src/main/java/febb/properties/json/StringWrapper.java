package febb.properties.json;

import java.util.Arrays;

public class StringWrapper {
    private NodeType nodeType;
    private String contents;
    public StringWrapper(String string) {
        char[] characters = string.toCharArray();
        char startCharacter = characters[0];
        char endCharacter = characters[characters.length-1];

        if (Node.START_OBJECT == startCharacter && Node.END_OBJECT == endCharacter) {
            char[] charArray = Arrays.copyOfRange(characters, 1, characters.length-1);
            this.contents = new String(charArray);
            this.nodeType = NodeType.OBJECT;
        } else if (Node.START_ARRAY == startCharacter && Node.END_ARRAY == endCharacter) {
            char[] charArray = Arrays.copyOfRange(characters, 1, characters.length-1);
            this.contents = new String(charArray);
            this.nodeType = NodeType.ARRAY;
        } else if (Node.START_STRING == startCharacter && Node.END_STRING == endCharacter) {
            char[] charArray = Arrays.copyOfRange(characters, 1, characters.length-1);
            this.contents = new String(charArray);
            this.nodeType = NodeType.STRING;
        } else {
            this.contents = new String(characters);
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

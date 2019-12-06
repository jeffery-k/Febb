package febb.properties.prototyped;

import febb.properties.json.Node;

import java.util.ArrayList;
import java.util.List;

public class GameConfig extends PrototypedConfig {
    private static final String BOARD_HEIGHT_KEY = "board_height";
    private static final String BOARD_WIDTH_KEY = "board_width";
    private static final String GAME_AGENTS_KEY = "game_characters";

    private int boardWidth;
    private int boardHeight;
    private List<String> gameAgentNames;

    public GameConfig(Node config) {
        super(config);
    }

    @Override
    public void init() {
        super.loadProperties();
        this.boardHeight = config.get(BOARD_HEIGHT_KEY).getIntegerValue();
        this.boardWidth = config.get(BOARD_WIDTH_KEY).getIntegerValue();
        this.gameAgentNames = new ArrayList<String>();
        Node gameAgentNamesNode = config.get(GAME_AGENTS_KEY);
        for (int i = 0; i < gameAgentNamesNode.size(); i++) {
            String gameAgentName = gameAgentNamesNode.get(i).getStringValue();
            this.gameAgentNames.add(gameAgentName);
        }
    }
}

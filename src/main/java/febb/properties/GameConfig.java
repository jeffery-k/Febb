package febb.properties;

import febb.properties.json.Node;

import java.util.ArrayList;
import java.util.List;

public class GameConfig extends PrototypedConfig<GameConfig> {
    private static final String BOARD_HEIGHT_KEY = "board_height";
    private static final String BOARD_WIDTH_KEY = "board_width";
    private static final String GAME_AGENTS_KEY = "game_characters";

    private int boardWidth;
    private int boardHeight;
    private List<String> gameAgents;
    public GameConfig(Node config) {
        super();
        this.prototype = config.get(PROTOTYPE_KEY).getBooleanValue();
        this.boardHeight = config.get(BOARD_HEIGHT_KEY).getIntegerValue();
        this.boardWidth = config.get(BOARD_WIDTH_KEY).getIntegerValue();
        this.gameAgents = new ArrayList<String>();
        for (Node agentNode : config.get(GAME_AGENTS_KEY)) {
            this.gameAgents.add(agentNode.getStringValue());
        }
    }

    @Override
    public void implement(GameConfig prototype) {
        int ting = 9;
        //TODO
    }
}

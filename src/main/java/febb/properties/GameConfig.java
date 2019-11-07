package febb.properties;

import febb.properties.json.Node;

import java.util.ArrayList;
import java.util.List;

public class GameConfig extends PrototypedConfig<GameConfig> {
    private static final String BOARD_HEIGHT = "board_height";
    private static final String BOARD_WIDTH = "board_width";
    private static final String GAME_CHARACTERS = "game_characters";

    private int boardWidth;
    private int boardHeight;
    private List<String> gameCharacters;
    public GameConfig(Node config) {
        super();
        this.prototype = config.get(PROTOTYPE_KEY).getBooleanValue();
        this.boardHeight = config.get(BOARD_HEIGHT).getFloorIntegerValue();
        this.boardWidth = config.get(BOARD_WIDTH).getFloorIntegerValue();
        this.gameCharacters = new ArrayList<String>();
        for (Node characters : config.get(GAME_CHARACTERS)) {
            this.gameCharacters.add(characters.getStringValue());
        }
    }

    @Override
    public void implement(GameConfig prototype) {
        //TODO
    }
}

package febb;

import febb.display.Display;
import febb.display.SlideShow;
import febb.game.SlideShowGame;
import febb.properties.BaseConfig;
import febb.properties.ConfigFileFactory;
import febb.strategy.SlideShowStrategyManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Febb {
    private static final String CONFIG_DIRECTORY = "config/config.json";
    private static final String DISPLAY_NAME = "Febb";
    private static final String GAME_SELECTION_PROMPT = "Select a game";
    private static final String ACTION_SELECTION_PROMPT = "Select an action";
    private static final String STRATEGY_SELECTION_PROMPT = "Select a strategy";

    private static final String OPTIMIZE_OPTION = "Optimize";
    private static final String VIEW_STRATEGIES_OPTION = "View Strategies";
    private static final String START_GAME_OPTION = "Start Game";
    private static final String[] OPTIONS = new String[]{OPTIMIZE_OPTION, VIEW_STRATEGIES_OPTION, START_GAME_OPTION};

    public static void main(String[] args) throws IOException {
        ConfigFileFactory propertiesFactory = new ConfigFileFactory(CONFIG_DIRECTORY);
        BaseConfig simulationProperties = propertiesFactory.getSimulationProperties();

        Display display = new Display(DISPLAY_NAME);
        display.activate();

        List<String> gameNames = simulationProperties.getConcreteGamePropertyKeys();
        String gameSelection = display.selectionInput(GAME_SELECTION_PROMPT, gameNames);
        SlideShowStrategyManager strategyManager = new SlideShowStrategyManager(simulationProperties, gameSelection, display);

        String actionSelection;
        List<String> options = Arrays.asList(OPTIONS);
        while (display.isActive()) {
            actionSelection = display.buttonInput(ACTION_SELECTION_PROMPT, options);
            if (OPTIMIZE_OPTION.equals(actionSelection)) {
                List<String> strategyNames = strategyManager.getStrategyNames();
                actionSelection = display.selectionInput(STRATEGY_SELECTION_PROMPT, strategyNames);
                SlideShow slideShow = strategyManager.slideShowOptimize(actionSelection);
                slideShow.play();
            } else if (VIEW_STRATEGIES_OPTION.equals(actionSelection)) {
                strategyManager.displayInfo();
            } else if (START_GAME_OPTION.equals(actionSelection)) {
                SlideShowGame game = new SlideShowGame(simulationProperties.getGameProperties(gameSelection), display);
                SlideShow slideShow = game.slideShowSimulate(strategyManager.getBestStrategies());
                slideShow.play();
            }
        }
    }
}

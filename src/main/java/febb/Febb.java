package febb;

import febb.display.Display;
import febb.display.SlideShow;
import febb.game.SlideShowGame;
import febb.properties.PropertiesFileFactory;
import febb.properties.SimulationProperties;
import febb.strategy.SlideShowStrategyManager;

import java.util.Arrays;
import java.util.List;

public class Febb {
    private final static String DISPLAY_NAME = "Febb";
    private final static String GAME_SELECTION_PROMPT = "Select a game";
    private final static String ACTION_SELECTION_PROMPT = "Select an action";
    private final static String STRATEGY_SELECTION_PROMPT = "Select a strategy";

    private final static String OPTIMIZE_OPTION = "Optimize";
    private final static String VIEW_STRATEGIES_OPTION = "View Strategies";
    private final static String START_GAME_OPTION = "Start Game";
    private final static String[] OPTIONS = new String[] {OPTIMIZE_OPTION, VIEW_STRATEGIES_OPTION, START_GAME_OPTION};

    public static void main(String[] args) {
        PropertiesFileFactory propertiesFactory = loadSimulationProperties();
        SimulationProperties simulationProperties = propertiesFactory.getSimulationProperties();

        Display display = new Display(DISPLAY_NAME);
        display.activate();

        List<String> gameNames = simulationProperties.getGameNames();
        String userSelection = display.selectionInput(GAME_SELECTION_PROMPT, gameNames);

        SlideShowStrategyManager strategyManager = new SlideShowStrategyManager(simulationProperties, userSelection, display);
        List<String> options = Arrays.asList(OPTIONS);
        while (display.isActive()) {
            userSelection = display.buttonInput(ACTION_SELECTION_PROMPT, options);
            if(OPTIMIZE_OPTION.equals(userSelection)) {
                List<String> strategyNames = strategyManager.getStrategyNames();
                userSelection = display.selectionInput(STRATEGY_SELECTION_PROMPT, strategyNames);
                SlideShow slideShow = strategyManager.slideShowOptimize(userSelection);
                slideShow.play();
            } else if(VIEW_STRATEGIES_OPTION.equals(userSelection)) {
                strategyManager.displayInfo();
            } else if(START_GAME_OPTION.equals(userSelection)) {
                SlideShowGame game = new SlideShowGame(simulationProperties.getGameProperties(), display);
                SlideShow slideShow = game.slideShowSimulate(strategyManager.getBestStrategies());
                slideShow.play();
            }
        }
    }

    private static PropertiesFileFactory loadSimulationProperties() {
        //TODO
        return null;
    }
}

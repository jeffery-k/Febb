package febb;

import febb.display.Display;

public class Febb {
    private final static String DISPLAY_NAME = "Febb";
    private final static String[] OPTIONS = new String[] {"Start Game", "View Strategies"};

    public static void main(String[] args) {
        Display display = new Display(DISPLAY_NAME);

        display.activate();
    }
}

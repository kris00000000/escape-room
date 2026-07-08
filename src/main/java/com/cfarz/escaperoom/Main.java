package com.cfarz.escaperoom;

import com.cfarz.escaperoom.controller.Game;
import com.cfarz.escaperoom.controller.GameControl;

public class Main {
    /**
     * In this class we start by initiating the entire game.
     *
     * @param args command-line arguments passed by launching the application.
     */
    public static void main(String[] args) {

        Game game = new Game();
        GameControl controller = new GameControl(game);
        controller.startGame();

    }
}

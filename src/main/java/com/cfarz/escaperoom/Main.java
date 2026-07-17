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
        System.out.println("""
                !!! EMERGENCY !!!

                There was a critical failure datected!

                The TimeFlow Laboratory has suffered a catastrophic
                time experiment. While scientist were working on it,
                the timeline broke it's continuity causing the Past,
                Present and Future Time Machines to shut down.

                Each machine contains a different Time Stone which is
                responsible of keeping the timeline continuity.

                If we don't repair the three Time Machines in less than
                10 minutes, the entire Universe will collapse and it will
                be impossible to ever fix it again. Therefore, you need to
                recover the three Time Stones, and restore the
                timeline before time collapses forever.

                You manage to enter the TimeFlow Laboratory and find yourself
                in a dark stone chamber. Will you be able to fix it on time?

                Good luck!

                """);
        Game game = new Game();
        GameControl controller = new GameControl(game);
        controller.startGame();

    }
}

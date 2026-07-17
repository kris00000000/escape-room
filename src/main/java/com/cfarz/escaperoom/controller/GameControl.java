package com.cfarz.escaperoom.controller;

import com.cfarz.escaperoom.model.*;
import java.util.Scanner;

public class GameControl {
    private final Game game;
    private final Scanner scanner;
    private boolean running;

    /**
     * Creates a controller.
     *
     * @param game the game.
     */
    public GameControl(Game game) {
        this.game = game;
        this.scanner = new Scanner(System.in);
        this.running = true;
    }

    /**
     * Starts the game and processes commands until the game stop.
     */
    public void startGame() {
        game.getTimer().start();

        System.out.println("Welcome to Time Travel Lab!");
        System.out.println("Repair the Past, Present and Future machines before time runs out!");

        while (running) {
            System.out.println();
            System.out.println("Time Remaining: " + game.getTimer().getFormattedTime());

            displayMenu();
            System.out.print("> ");
            String choice = scanner.nextLine();
            processCommand(choice);

            if (game.getTimer().isTimeUp()) {
                System.out.println("Time has run out! The timeline collapsed and the entire universe got destroyed!!!");
                running = false;
            }
        }
        scanner.close();
    }

    /**
     * Processes a command.
     *
     * @param choice the player's choice.
     */
    private void processCommand(String choice) {
        switch (choice) {
            case "1":
                look();
                break;

            case "2":
                inventory();
                break;

            case "3":
                inspect();
                break;

            case "4":
                take();
                break;

            case "5":
                drop();
                break;

            case "6":
                use();
                break;

            case "7":
                repair();
                break;

            case "8":
                move();
                break;

            case "9":
                solvePuzzle();
                break;

            case "10":
                running = false;
                System.out.println("Game ended.");
                break;

            default:
                System.out.println("Please choose 1-10.");
        }
    }

    /**
     * Shows the possible game commands.
     */
    private void displayMenu() {
        System.out.println();
        System.out.println("Things you can do:");
        System.out.println("1. Look around");
        System.out.println("2. Inventory");
        System.out.println("3. Inspect object");
        System.out.println("4. Take item");
        System.out.println("5. Drop item");
        System.out.println("6. Use item");
        System.out.println("7. Repair machine");
        System.out.println("8. Move room");
        System.out.println("9. Solve combination");
        System.out.println("10. Quit");
        System.out.println("");

    }

    /**
     * Shows the information about the player's room and items.
     */
    private void look() {
        Player player = game.getPlayer();
        Room room = player.getCurrentRoom();
        System.out.println(room.inspect());
        room.displayItems();
    }

    /**
     * Shows the player's inventory.
     */
    private void inventory() {
        game.getPlayer().getInventory().displayInventory();
    }

    /**
     * Lets the pñayer inspect an object in the room.
     */
    private void inspect() {
        System.out.print("What do you want to inspect? ");
        String object = scanner.nextLine();
        Room room = game.getPlayer().getCurrentRoom();

        for (Item item : room.getItems()) {
            if (item.getName().equalsIgnoreCase(object)) {
                System.out.println(item.inspect());
                return;
            }
        }

        if (room.getMachine() != null && room.getMachine().getName().equalsIgnoreCase(object)) {
            System.out.println(room.getMachine().inspect());
            return;
        }
        System.out.println("You cannot inspect that.");
    }

    /**
     * Lets the player take an item from the room.
     */
    private void take() {
        System.out.print("Take what? ");
        String item = scanner.nextLine();
        game.getPlayer().takeItem(item);

    }

    /**
     * Drops an item into the room.
     */
    private void drop() {
        System.out.print("Drop what? ");
        String item = scanner.nextLine();
        game.getPlayer().dropItem(item);

    }

    /**
     * To use an item from the inventory.
     */
    private void use() {
        Player player = game.getPlayer();
        System.out.print("Use what? ");
        String item = scanner.nextLine();

        if (item.equalsIgnoreCase("matches lantern")) {
            Item lantern = player.getInventory().getItem("Lantern");
            Item matches = player.getInventory().getItem("Matches");

            if (lantern instanceof Lantern && matches != null) {
                ((Lantern) lantern).light(player.getCurrentRoom());
            } else {
                System.out.println("You need the lantern and matches.");
            }
            return;
        }
        player.useItem(item);
    }

    /**
     * Repairs the machine using a code.
     */
    private void repair() {
        Room room = game.getPlayer().getCurrentRoom();
        Machine machine = room.getMachine();

        if (machine == null) {
            System.out.println("There is no machine here.");
            return;
        }

        System.out.print("Enter machine code: ");
        String code = scanner.nextLine();
        machine.repair(code, game.getPlayer());
        checkWin();
    }

    /**
     * The player tries solving a puzzle in the current room.
     */
    private void solvePuzzle() {
        Room room = game.getPlayer().getCurrentRoom();

        if (room.getPuzzles().isEmpty()) {
            System.out.println("There are no puzzles to help you here.");
            return;
        }

        for (Puzzle puzzle : room.getPuzzles()) {
            if (!puzzle.isSolved()) {
                System.out.println(puzzle.inspect());
                System.out.print("Solution: ");

                String answer = scanner.nextLine();
                puzzle.solve(answer);
                return;
            }
        }
        System.out.println("All puzzles here are solved.");
    }

    /**
     * Moves the player to another room.
     */
    private void move() {
        Player player = game.getPlayer();
        Room current = player.getCurrentRoom();
        System.out.print("Where do you want to go? Past Present or Future?");

        String destination = scanner.nextLine();
        Room next = current.getExit(destination);

        if (next == null) {
            System.out.println("You cannot go there.");
            return;
        }

        Door door = current.getDoor();

        if (door != null && door.isLocked()) {
            door.interact(player);

            if (door.isLocked()) {
                return;
            }
        }
        player.move(next);
        System.out.println("You entered the " + next.getName() + " room.");
    }

    /**
     * Checks if the player has collected all Time Stones.
     * If all stones are collected, the game is completed.
     */
    private void checkWin() {
        Inventory inventory = game.getPlayer().getInventory();

        if (inventory.hasItem("Green Time Stone")
                &&
                inventory.hasItem("Yellow Time Stone")
                &&
                inventory.hasItem("Blue Time Stone")) {

            System.out.println();
            System.out.println("""

                    The three Time Stones begin to shine.

                    The green stone restores the memories
                    of the past while the yellow stone stabilizes
                    the present and the blue on rebuilds the future.

                    The main time reactor is finally back on and the
                    three broken splits finally merge together to form
                    a continuous timeline.

                    The final exit door opens.

                    You walk out of the TimeFlow Laboratory,
                    knowing that time itself has been saved.

                    Congratulations, you restored the timeline
                    and saved the world!

                    =====================================
                    """);
            running = false;
        }
    }
}

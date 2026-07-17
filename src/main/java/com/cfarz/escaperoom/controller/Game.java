package com.cfarz.escaperoom.controller;

import com.cfarz.escaperoom.model.*;
import com.cfarz.escaperoom.util.Timer;

import java.util.Arrays;

public class Game {

    private Player player;
    private Room pastRoom;
    private Room presentRoom;
    private Room futureRoom;
    private Timer timer;

    // Games to find the combination.
    private Puzzle symbolPuzzle;
    private Puzzle wirePuzzle;
    private Puzzle laserPuzzle;

    // Machines
    private Machine pastMachine;
    private Machine presentMachine;
    private Machine futureMachine;

    /**
     * Creates a new game and initializes everything.
     */
    public Game() {
        createRooms();
        createItems();
        createPuzzles();
        createMachines();
        createDoors();

        player = new Player("Player", pastRoom);
        timer = new Timer(600);
    }

    /**
     * Creates all rooms and creates connections between them.
     */
    private void createRooms() {

        pastRoom = new Room(
                "Past",
                "You're in an ancient stone chamber.");
        pastRoom.setDark(true);

        presentRoom = new Room(
                "Present",
                "A damaged laboratory. Emergency lights are flickering.");

        futureRoom = new Room(
                "Future",
                "A futuristic control room controlled by AI.");

        pastRoom.addExit("present", presentRoom);
        presentRoom.addExit("past", pastRoom);
        presentRoom.addExit("future", futureRoom);
        futureRoom.addExit("present", presentRoom);

    }

    /**
     * Creates and puts all items in their rooms.
     */
    private void createItems() {
        /*
         * PThe room from the past
         */

        Lantern lantern = new Lantern();
        lantern.setVisibleInDark(true);

        Item matches = new Item(
                "Matches",
                "A box of matches used to light the lantern.");
        matches.setVisibleInDark(true);

        Book ancientBook = new Book(
                "Ancient Book",
                """
                        TIMEFLOW LABORATORY ARCHIVE
                        ENTRY 001: THE BEGINNING OF TIME TRAVEL

                        Before the TimeFlow Laboratory became a place
                        of advanced technology, some scientists discovered
                        that time could be influenced.

                        The first Time Machine was created to study the Past.
                        However, controlling only one tense of time created instability.
                        Therefore it was decided to create 3 time machines, each 
                        powered by a different stone.

                        Green Stone - Keeps the past timeline.
                        Yellow Stone - Keeps the present timeline.
                        Blue Stone - Keeps the future timeline.

                        The first machine was protected by a code that was 
                        created using an ancient symbol system.

                        The symbols were placed in the order that
                        represented the beginning of the civilization:

                        Sun -> Tree -> River -> Mountain

                        In order to restore the past machine you need to 
                        understand how the past was created and find the 
                        clues other scientist left.

                        """);

        Item stoneTable = new Item(
                "Stone Table",
                "The code for each sacred symbols is:\n\n"
                        + "Tree: 8\n\n"
                        + "River: 1\n\n"
                        + "Sun: 4\n\n"
                        + "Mountain: 6\n\n");

        pastRoom.addItem(lantern);
        pastRoom.addItem(matches);
        pastRoom.addItem(ancientBook);
        pastRoom.addItem(stoneTable);

        /*
         * The room for the present.
         */

        Item computer = new Item(
                "Computer",
                "A damaged computer that needs power.");

        Item whiteboard = new Item(
                "Whiteboard",
                "The number for each wire is: \n\n"
                        + "Red: 6\n\n"
                        + "Green: 9\n\n"
                        + "Blue: 2\n\n"
                        + "Yellow: 0\n\n");

        Item battery = new Item(
                "Battery",
                "A battery for the power unit.");

        Item fuseBox = new Item(
                "Fuse Box",
                "The fuse box has a blown fuse.");

        Item wireCutters = new Item(
                "Wire Cutters",
                "Tools used to repair wires.");

        Book presentBook = new Book(
                "Present History Book",
                """
                        TIMEFLOW LABORATORY ARCHIVE
                        ENTRY 403: THE TIMELINE FRACTURE

                        As scientists were experimenting the Time reactor 
                        overloaded, this caused all three machines to stop 
                        working, cousing a split between three time periods.

                        The Past Machine stopped first. Then the Present 
                        machine lost the electrical power and the Future 
                        machine activated its security systems and blocked 
                        everything.

                        To restore the Present Machine, the power system
                        must be repaired. The wires need to be placed in 
                        a specific flow sequence:

                        Red -> Blue -> Yellow -> Green

                        Once the code is inserted on the Present machine,
                        the machine will be restarted and you'll get the Time stone.

                        """);

        presentRoom.addItem(computer);
        presentRoom.addItem(whiteboard);
        presentRoom.addItem(battery);
        presentRoom.addItem(fuseBox);
        presentRoom.addItem(wireCutters);
        presentRoom.addItem(presentBook);

        /*
         * The futuristic room.
         */

        Item aiTerminal = new Item(
                "AI Terminal",
                "An AI waiting for memory fragments.");

        Item hologram = new Item(
                "Hologram Projector",
                "A laser puzzle projector.");

        Book futureBook = new Book(
                "Future Book",
                """
                        TIMEFLOW AI ARCHIVE
                        ENTRY 4137: THE FINAL TIMELINE

                        The future was created from the choices
                        made in the past and in the present.

                        When the timeline fractured, the Future Machine
                        entered emergency security mode since the past and 
                        present were both broken.

                        To prevent the destruction of time itself,
                        the AI divided its memory into several fragments.

                        The memory chips contain the information needed
                        to access the Future Machine and restore it.

                        Once the AI terminal is unlocked, the timeline will be restored.

                        Past.
                        Present.
                        Future.

                        """);

        MemoryChip chip1 = new MemoryChip("Memory Chip 1", 9);
        MemoryChip chip2 = new MemoryChip("Memory Chip 2", 3);
        MemoryChip chip3 = new MemoryChip("Memory Chip 3", 7);
        MemoryChip chip4 = new MemoryChip("Memory Chip 4", 4);

        Item mainConsole = new Item("Main Console", "Controls the Future Machine.");

        futureRoom.addItem(aiTerminal);
        futureRoom.addItem(hologram);
        futureRoom.addItem(futureBook);
        futureRoom.addItem(chip1);
        futureRoom.addItem(chip2);
        futureRoom.addItem(chip3);
        futureRoom.addItem(chip4);
        futureRoom.addItem(mainConsole);

    }

    /**
     * Creates and assigns puzzles to their rooms.
     */
    private void createPuzzles() {
        symbolPuzzle = new Puzzle(
                "Stone Symbol Puzzle",
                "Rotate symbols:\nSun -> Tree -> River -> Mountain",
                "4816");
        pastRoom.addPuzzle(symbolPuzzle);

        wirePuzzle = new Puzzle(
                "Wire Puzzle",
                "Connect:\nRed -> Blue -> Yellow -> Green",
                "6209");
        presentRoom.addPuzzle(wirePuzzle);

        laserPuzzle = new Puzzle(
                "Laser Puzzle",
                "Redirect the laser into the reactor.",
                "9374");

        futureRoom.addPuzzle(laserPuzzle);
    }

    /**
     * Creates all machines.
     */
    private void createMachines() {
        Item greenStone = new Item(
                "Green Time Stone",
                "The Past Time Stone.");

        pastMachine = new Machine(
                "Clockwork Machine",
                "An old clockwork machine powered by gears.",
                "4816",
                greenStone);

        pastMachine.setRequiredPuzzle(symbolPuzzle);
        pastRoom.setMachine(pastMachine);

        Item yellowStone = new Item(
                "Yellow Time Stone",
                "The Present Time Stone.");

        presentMachine = new Machine(
                "Present Machine",
                "A damaged electrical Time Machine.",
                "6209",
                yellowStone);

        presentMachine.setRequiredPuzzle(wirePuzzle);
        presentRoom.setMachine(presentMachine);

        Item blueStone = new Item(
                "Blue Time Stone",
                "The Future Time Stone.");

        futureMachine = new Machine(
                "Future Machine",
                "A heavily encrypted AI machine.",
                "9374",
                blueStone);

        futureMachine.setRequiredPuzzle(laserPuzzle);
        futureRoom.setMachine(futureMachine);
    }

    /**
     * Creates doors and places their requirements.
     */
    private void createDoors() {
        pastRoom.setDoor(new Door(
                "Past Door",
                "A locked stone door.",
                Arrays.asList("Green Time Stone")));

        presentRoom.setDoor(new Door(
                "Present Door",
                "A locked laboratory door.",
                Arrays.asList("Green Time Stone", "Yellow Time Stone")));

        futureRoom.setDoor(new Door(
                "Final Exit",
                "The final exit through time.",
                Arrays.asList("Green Time Stone", "Yellow Time Stone", "Blue Time Stone")));

    }

    /**
     * Gets the current player.
     *
     * @return the player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Getter for the game timer.
     *
     * @return the timer.
     */
    public Timer getTimer() {
        return timer;
    }
}

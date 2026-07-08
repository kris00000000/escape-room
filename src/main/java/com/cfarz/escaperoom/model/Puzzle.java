package com.cfarz.escaperoom.model;

import com.cfarz.escaperoom.interfaces.Interactable;
import com.cfarz.escaperoom.interfaces.Inspectable;

public class Puzzle implements Interactable, Inspectable {

    private String name;
    private String description;

    private String solution;
    private boolean solved;

    /**
     * Creates a new puzzle.
     *
     * @param name the name.
     * @param description the description.
     * @param solution the correct solution.
     */
    public Puzzle(String name, String description, String solution) {
        this.name = name;
        this.description = description;
        this.solution = solution;
        this.solved = false;
    }

    /**
     * Getter for the name of the puzzle.
     *
     * @return the puzzle name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the description of the puzzle.
     *
     * @return the puzzle description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the puzzle has been solved.
     *
     * @return true if the puzzle is solved.
     */
    public boolean isSolved() {
        return solved;
    }

    /**
     * Inspects the puzzle.
     *
     * @return a message indicating the puzzle is solved or description.
     */
    @Override
    public String inspect() {

        if (solved) {
            return "The puzzle has already been solved.";
        }
        return description;
    }

    /**
     * Lets the player to interact with the puzzle.
     *
     * @param player the player.
     */
    @Override
    public void interact(Player player) {

        if (solved) {
            System.out.println("This puzzle is already solved.");
            return;
        }

        System.out.println("Enter the solution for: " + name);
    }

    /**
     * Used to solve the puzzle using a given answer.
     *
     * @param answer the answer given by the player.
     * @return true if the puzzle is solved.
     */
    public boolean solve(String answer) {

        if (solved) {
            System.out.println("This puzzle is already solved.");
            return false;
        }

        if (answer.equalsIgnoreCase(solution)) {
            solved = true;

            System.out.println("Puzzle solved: " + name);
            return true;
        }

        System.out.println("Incorrect solution.");

        return false;
    }
}

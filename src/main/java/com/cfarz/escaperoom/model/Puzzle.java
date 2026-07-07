package com.cfarz.escaperoom.model;

import com.cfarz.escaperoom.interfaces.Interactable;
import com.cfarz.escaperoom.interfaces.Inspectable;

public class Puzzle implements Interactable, Inspectable {

    private String name;
    private String description;

    private String solution;
    private boolean solved;

    public Puzzle(String name, String description, String solution) {
        this.name = name;
        this.description = description;
        this.solution = solution;
        this.solved = false;
    }

    public String getName() {
        return name;
    }

    public boolean isSolved() {
        return solved;
    }

    @Override
    public String inspect() {

        if (solved) {
            return "The puzzle has already been solved.";
        }

        return description;
    }

    @Override
    public void interact(Player player) {

        if (solved) {
            System.out.println("This puzzle is already solved.");
            return;
        }

        System.out.println("Enter the puzzle solution:");

    }

    public boolean solve(String answer) {

        if (answer.equals(solution)) {

            solved = true;
            System.out.println("Puzzle solved!");

            return true;
        }

        System.out.println("Incorrect solution.");

        return false;
    }
}
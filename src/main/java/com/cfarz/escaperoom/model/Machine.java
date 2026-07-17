package com.cfarz.escaperoom.model;

import com.cfarz.escaperoom.interfaces.Interactable;
import com.cfarz.escaperoom.interfaces.Inspectable;

public class Machine implements Interactable, Inspectable {

    private final String name;
    private final String description;
    private final String code;

    private boolean repaired;
    private Item rewardStone;
    private Puzzle requiredPuzzle;

    /**
     * Creates a new machine with the specified details and reward.
     *
     * @param name the machine.
     * @param description the description.
     * @param code the code to repair the machine.
     * @param rewardStone the item given after repairing the machine.
     */
    public Machine(String name, String description, String code, Item rewardStone) {

        this.name = name;
        this.description = description;
        this.code = code;
        this.rewardStone = rewardStone;
        this.repaired = false;
    }

    /**
     * Getter for the machine.
     *
     * @return the machine name.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the machine has been repaired.
     *
     * @return true if the machine is repaired, otherwise false
     */
    public boolean isRepaired() {
        return repaired;
    }

    /**
     * Getter for the stone given by the machine.
     *
     * @return the stone.
     */
    public Item getRewardStone() {
        return rewardStone;
    }

    /**
     * Setter for the puzzle needed before the machine can be repaired.
     *
     * @param puzzle the puzzle required to unlock.
     */
    public void setRequiredPuzzle(Puzzle puzzle) {
        this.requiredPuzzle = puzzle;
    }

    /**
     * Getter for the puzzle needed to repair the machine.
     *
     * @return the puzzle
     */
    public Puzzle getRequiredPuzzle() {
        return requiredPuzzle;
    }

    /**
     * Inspects the machine.
     *
     * @return a description or saying it's been repaired.
     */
    @Override
    public String inspect() {
        if (repaired) {
            return name + " has been repaired and works again.";
        }
        return description;
    }

    /**
     * Interacts with the machine.
     *
     * @param player the player.
     */
    @Override
    public void interact(Player player) {
        if (repaired) {
            System.out.println("The machine is already repaired.");
            return;
        }

        System.out.println("The " + name + " requires a code.");
    }

    /**
     * Repairs the machine using a code.
     *
     * @param enteredCode the code.
     * @param player the player.
     * @return true if the machine is repaired.
     */
    public boolean repair(String enteredCode, Player player) {

        if (repaired) {
            System.out.println("The machine is already repaired.");
            return false;
        }

        if (requiredPuzzle != null && !requiredPuzzle.isSolved()) {
            System.out.println("The machine is locked. Solve the puzzle and find the combination first.");
            return false;
        }

        if (enteredCode.equals(code)) {
            repaired = true;

            System.out.println("The " + name + " is finally working again!");

            if (rewardStone != null) {
                player.getInventory().addItem(rewardStone);

                System.out.println("You received the " + rewardStone.getName());
            }
            return true;
        }

        System.out.println("Incorrect code.");
        return false;
    }
}

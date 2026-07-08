package com.cfarz.escaperoom.model;

/**
 * Represents a memory chip item that contains a numerical value.
 * The player can interact with the memory chip to reveal the stored number.
 */
public class MemoryChip extends Item {

    private final int number;

    /**
     * Creates a new chip.
     *
     * @param name the memory chip.
     * @param number the number stored inside the memory chip.
     */
    public MemoryChip(String name, int number) {
        super(name, "A memory fragment containing a number.");
        this.number = number;
    }

    /**
     * Getter for the number stored in the memory chip.
     *
     * @return the stored number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Interacts with the memory chip and displays the stored number.
     *
     * @param player the player.
     */
    @Override
    public void interact(Player player) {
        System.out.println(getName() + " contains the number: " + number);
    }

    /**
     * Looks at the memory chip and returns information.
     *
     * @return a description containing the stored number
     */
    @Override
    public String inspect() {
        return getName() + " contains the number: " + number;
    }
}

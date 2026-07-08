package com.cfarz.escaperoom.model;

/**
 * Represents a book item that contains readable information.
 */
public class Book extends Item {

    private String contents;

    /**
     * Creates a new book.
     *
     * @param name the name of the book.
     * @param contents the information contained inside the book.
     */
    public Book(String name, String contents) {
        super(name, "An old book containing important information.");
        this.contents = contents;
    }

    /**
     * Inspects the book .
     *
     * @return the contents of the book.
     */
    @Override
    public String inspect() {
        return contents;
    }

    /**
     * Getter for the contents of the book.
     *
     * @return the book contents.
     */
    public String getContents() {
        return contents;
    }
}

package io;

public enum ConsoleColor {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    MAGENTA("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m");

    private final String colorCode;

    ConsoleColor(String colorCode) {
        this.colorCode = colorCode;
    }

    // Override toString() to return color code directly
    @Override
    public String toString() {
        return colorCode;
    }
}


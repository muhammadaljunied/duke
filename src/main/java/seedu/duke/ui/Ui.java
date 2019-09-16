package seedu.duke.ui;

import seedu.duke.exceptions.DukeException;

import java.util.Scanner;

/**
 * The Ui Class is static as there is exactly one Ui in Duke, and more should not be instantiated.
 * A Singleton implementation would have been excessive for a utility class.
 */
public class Ui {

    private static final String HORIZONTAL_LINE = "______________________________"
        + "______________________________";

    private static final String LOGO = "\t ____        _        \n"
        + "\t|  _ \\ _   _| | _____ \n"
        + "\t| | | | | | | |/ / _ \\\n"
        + "\t| |_| | |_| |   <  __/\n"
        + "\t|____/ \\__,_|_|\\_\\___|\n";

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Prints the greeting message.
     */
    public static void greet() {
        System.out.println(LOGO);
        printMessages("Hello! I'm Duke", "What can I do for you?");
    }

    /**
     * Prints out the variable message arguments, each on its own line.
     * @param messages The variable message arguments to print.
     */
    public static void printMessages(String... messages) {
        printLine();
        for (String message : messages) {
            System.out.println("\t" + message);
        }
        printLine();
    }

    public static void printLoadingError() {
        printMessages("Creating a new taskList for you...");
    }

    public static void printError(DukeException e) {
        printMessages(e.getMessage());
    }

    public static String nextLine() {
        return scanner.nextLine();
    }

    /**
     * Print horizontal line.
     */
    private static void printLine() {
        System.out.println("\t" + HORIZONTAL_LINE);
    }

}
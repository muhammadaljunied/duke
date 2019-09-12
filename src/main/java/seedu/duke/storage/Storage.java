package seedu.duke.storage;

import seedu.duke.trackables.Deadline;
import seedu.duke.trackables.Event;
import seedu.duke.trackables.Task;
import seedu.duke.trackables.Todo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Storage {

    /**
     * Default file path used if the user doesn't provide the file name.
     */
    private static final String DEFAULT_STORAGE_PATH = "/data/tasksfile.txt";

    private static Storage instance;

    private final Path path;

    private Storage() {

        path = Paths.get(System.getProperty("user.dir") + DEFAULT_STORAGE_PATH);
    }

    /**
     * Gets the Singleton instance of the Storage Object.
     *
     * @return the Storage instance.
     */
    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }


    /**
     * Saves the list of tasks to the storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void saveToDisk(List<Task> tasks) throws StorageOperationException {
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }

            List<String> tasksAsString = getStringsFromTasks(tasks);
            Files.write(path, tasksAsString);
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path, ioe);
        }
    }

    /**
     * Loads the Tasks data from the storage file, and then returns it.
     * Returns an empty {@code AddressBook} if the file does not exist, or is not a regular file.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public List<Task> loadFromDisk() throws StorageOperationException {

        if (!Files.exists(path)) {
            return new ArrayList<Task>();
        }

        try {
            return getTasksFromStrings(Files.readAllLines(path));
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path, ioe);
        } catch (Exception e) {
            throw new StorageOperationException("Fatal error occured. Coud not load tasks.", e);
        }
    }

    /**
     * Converts all the {@code tasks} as their equivalent Strings.
     *
     * @param tasks Lists containing all the tasks to convert.
     * @return Returns a list of Tasks in their String equivalent form.
     */
    private List<String> getStringsFromTasks(List<Task> tasks) {
        return tasks.stream().map(Task::getAsString).collect(Collectors.toList());
    }

    /**
     * Converts all the {@code stringTasks} as their equivalent Task Objects.
     *
     * @param stringTasks List containing all tasks in their string forms.
     * @return
     */
    private List<Task> getTasksFromStrings(List<String> stringTasks) {
        return stringTasks.stream()
            .map(identifyTask -> identifyTask.split(" \\| "))        // Split String
            .map(this::stringArgsToTask).collect(Collectors.toList());     // Run each String[] through stringToTask()
    }

    /**
     * Converts a single set of String Arguments into their respective Task subclass.
     *
     * @param args Contains the task data.
     * @return Returns the populated task subclass.
     */
    private Task stringArgsToTask(String... args) {
        switch (args[0]) {
        case "T":
            return new Todo(args);
        case "E":
            return new Event(args);
        case "D":
            return new Deadline(args);
        default:
            return new Task(args);
        }
    }

    public String getPath() {
        return path.toString();
    }

    /**
     * Signals that some error has occurred while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message, Throwable cause) {
            super(message, cause);
        }
    }


}

package seedu.duke.commands;

import seedu.duke.trackables.Task;
import seedu.duke.trackables.Todo;

import java.util.List;

public class TodoCommand extends Command {

    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(List<Task> tasks) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        echo("Got it. I've added this task:",
            "  " + todo.toString(),
            "Now you have " + tasks.size() + " tasks in the list.");
    }
}

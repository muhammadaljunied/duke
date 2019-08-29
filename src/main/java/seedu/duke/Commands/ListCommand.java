package seedu.duke.Commands;

import seedu.duke.Task;

import java.util.List;

public class ListCommand extends Command {
    private Task task;

    public ListCommand() {
    }

    @Override
    public void execute(List<Task> tasks) {
        String[] message = new String[tasks.size()];

        for (int i = 0; i < tasks.size(); i++) {
            message[i] = "\t" + (i + 1) + "." + tasks.get(i).getDescriptionWithStatus();
        }
        echo(message);
    }
}

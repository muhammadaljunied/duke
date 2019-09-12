package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.trackables.Task;

import java.util.List;

public class ByeCommand extends Command {

    private Task task;

    @Override
    public void execute(List<Task> tasks) {
        echo(new String[]{"\t" + "Bye. Hope to see you again soon!"});
        System.exit(0);
    }
}

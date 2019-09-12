package seedu.duke.trackables;


public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {

    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String... args) {
        this.isDone = !args[1].equals("0");
        this.description = args[2];
    }

    private String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Gets the Task in its String equivalent form.
     *
     * @return
     */
    public String getAsString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(isDone ? "1" : "0").append(" | ").append(this.description);
        return sb.toString();
    }
}
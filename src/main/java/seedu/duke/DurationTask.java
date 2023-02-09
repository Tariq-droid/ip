package seedu.duke;

public class DurationTask extends ToDo{

    /**
     * Constructs a duration task object and initializes the needed parameters.
     *
     * @param description description of the task.
     */
    protected String time = "";
    public DurationTask(String description, String time) {
        super(description);
        this.time = time;
    }

    public String toString() {
        return super.toString() + "needs (" + this.time + ")";
    }
}

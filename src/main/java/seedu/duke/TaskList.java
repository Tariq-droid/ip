package seedu.duke;

import java.util.ArrayList;

/**
 * Class that contains the tasks and the actions to do on them.
 * it extends the Task class.
 *
 * @param list ArrayList of tasks containing the tasks.
 */
public class TaskList {
    protected ArrayList<Task> list;

    public TaskList(ArrayList<Task> list){
        this.list = list;
    }
    public TaskList(){
        this.list = new ArrayList<Task>();
    }

    /**
     * Marks a task as done.
     *
     * @param index the index of the task that is to be marked.
     */
    public void mark(int index){
        try {
            list.get(index).isDone = true;
            System.out.println("    -------------------------------------------");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("    " + "[" + list.get(index).getStatusIcon() + "] " + list.get(index).description);
            System.out.println("    -------------------------------------------");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("caught IOOBE");
        } catch (NullPointerException n) {
            System.out.println("caught NPE");
        }
    }

    /**
     * Marks a task as being not done.
     *
     * @param index the index of the task that is to be unmarked.
     */
    public void unmark(int index){
        list.get(index).isDone = false;
        System.out.println("    -------------------------------------------");
        System.out.println("Nice! I've marked this task as not done yet:");
        System.out.println("    " +
                "[" + list.get(index).getStatusIcon() + "] "
                + list.get(index).description);
        System.out.println("    -------------------------------------------");
    }

    /**
     * prints the tasks.
     */
    public String showList(){
        String reply = "";
        System.out.println("    -------------------------------------------");
        for (int i = 0; i < list.size(); i++) {
            reply += "    " + String.valueOf(i + 1) + "."  + list.get(i).toString() +"\n";
            System.out.println("    " + String.valueOf(i + 1) + "."  + list.get(i).toString());
        }
        System.out.println("    -------------------------------------------");
        return reply;
    }

    /**
     * Adds a ToDo task to the list.
     *
     * @param echoSplit an array of String containing the words of the command given.
     */
    public void addToDo(String[] echoSplit){
        String task = "";
        assert echoSplit.length > 0 : "task length should not be less than 0";
        for (int i = 1; i < echoSplit.length; i++) {
            task += echoSplit[i] + " ";
        }
        list.add(new ToDo(task));
        System.out.println("    -------------------------------------------\n    "
                + "added: " + task
                +"\n    -------------------------------------------");
    }

    /**
     * Adds an Event task to the list.
     *
     * @param echoSplit an array of String containing the words of the command given.
     * @param print an int that specifies whether to print the action or not.
     */
    public void addEvent(String[] echoSplit, int print){
        String task = "";
        int fromIndex = 0;
        int toIndex = 0;
        String fromDate = "";
        String toDate = "";
        assert echoSplit.length > 0 : "task length should not be less than 0";

      
        


        for (int i = 1; i < echoSplit.length; i++) {
            boolean isFromWord = echoSplit[i].equals("/from") || echoSplit[i].equals("from:");
            boolean isToWord = echoSplit[i].equals("/to") || echoSplit[i].equals("to:");

            if(isFromWord) {
                fromIndex = i;
            }

            if(isToWord) {
                toIndex = i;

                for (int j = fromIndex + 1; j < toIndex; j++) {
                    if(j == toIndex - 1) {
                        fromDate += echoSplit[j];
                    } else {
                        fromDate += echoSplit[j] + " ";
                    }
                }
                for (int j = toIndex + 1; j < echoSplit.length; j++) {
                    if(j == echoSplit.length - 1) {
                        toDate += echoSplit[j];
                    } else {
                        toDate += echoSplit[j] + " ";
                    }
                }
                for (int j = 1; j < fromIndex; j++) {
                    task += echoSplit[j] + " ";
                }
                break;
            }


        }
        list.add(new Event(task, fromDate, toDate));
        if(print == 0)
            System.out.println("    -------------------------------------------\n    "
                    + "added: " + task
                    +"\n    -------------------------------------------");
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param echoSplit an array of String containing the words of the command given.
     * @param print an int that specifies whether to print the action or not.
     */
    public void addDeadline(String[] echoSplit, int print){
        String task = "";
        String date = "";

        for (int i = 1; i < echoSplit.length; i++) {
            boolean isByWord = echoSplit[i].equals("/by") || echoSplit[i].equals("by:");
            if(isByWord){

                for (int j = 1; j < i; j++) {
                    if(j == i-1) {
                        task += echoSplit[j];
                    } else {
                        task += echoSplit[j] + " ";
                    }
                }
                for (int j = i + 1; j < echoSplit.length; j++) {
                    if(j == echoSplit.length - 1) {
                        date += echoSplit[j];
                    } else {
                        date += echoSplit[j] + " ";
                    }
                }

                list.add(new Deadline(task, date));
                if(print == 0) {
                    System.out.println(echoSplit[i+1]);
                    System.out.println("    -------------------------------------------\n    "
                            + "added: " + task
                            +"\n    -------------------------------------------");
                }

            }
        }
    }

    /**
     * Deletes a task from the list.
     * Prints the task that was deleted.
     */
    public void delete(int index){
        System.out.println("    -------------------------------------------\n    "
                + "removed: " + list.get(index)
                + "\n    -------------------------------------------");
        list.remove(index);
    }

    public String find(String[] echoSplit) {
        System.out.println("    -------------------------------------------");
        String keyword = echoSplit[1];
        for (int i = 0; i < list.size(); i++) {
            String description = list.get(i).description;
            String[] taskSplit = description.split(" ");
            for (int j = 0; j < taskSplit.length; j++) {
                if (keyword.equals(taskSplit[j])) {

                    System.out.println("    " + String.valueOf(i + 1) + "."  + list.get(i).toString());
                    return "    " + String.valueOf(i + 1) + "."  + list.get(i).toString();

                }
            }
        }
        System.out.println("    -------------------------------------------");
        return "not found";
    }
}

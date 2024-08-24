package com.taskmanager.core;

/**
 * The {@code Task} class represents an objective to be completed. This class
 * uses {@link CurrentState} to represent the current state of a task.
 * <p>
 * Tasks created have their current state set to
 * {@code CurrentState.NOT_STARTED} and have a default priority level of 1.
 * The maximum priority of a task cannot exceed 5.
 * </p>
 * <p>
 * Here are the following ways to create a Task object.
 * <blockquote><pre>
 *     Task myTask = new Task("myTask");
 * </pre></blockquote>
 * <blockquote><pre>
 *     Task myTask2 = new Task("myTask2", "details regarding task2");
 * </pre></blockquote>
 * <blockquote><pre>
 *     Task myTask3 = new Task("myTask3", "details regarding task3", 5);
 * </pre></blockquote>
 * </p>
 * <p>
 * This class also contains methods for starting and completing tasks. It
 * also contains a method to check if two tasks are equal. It checks the
 * priority level first, then their current state and finally, their name.
 * The reason to make checks in this order is to also ensure sorting by
 * priority, current state and then name.
 * </p>
 *
 * @author Mac
 * @see     CurrentState
 * @see     Comparable
 * @since 1.0
 */
public class Task implements Comparable<Task>{
    private CurrentState currentState = CurrentState.NOT_STARTED;
    private final int MAX_PRIORITY = 5;

    private String name;
    private String details;
    private int priority;

    public Task(String name) {
        this(name, "None");
    }

    public Task(String name, String details) {
        this(name, details, 1);
    }

    public Task(String name, String details, int priority) {
        if (check(name, details, priority)){
            this.name = name;
            this.details = details;
            this.priority = priority;
        } else throw new IllegalArgumentException("Please check name, details "
                + "and/or priority of task!");
    }

    /**
     * A method to start a task and set current state to {@code
     * CurrentState.IN_PROGRESS}.
     * <p>
     * Once a task is in progress, it's current
     * state can not be changed back to {@code CurrentState.NOT_STARTED}
     * </p>
     */
    public void startTask(){
        System.out.println("Task started!");
        currentState = CurrentState.IN_PROGRESS;
    }

    /**
     * A method to complete a task and set current state to {@code
     * CurrentState.FINISHED}.
     * <p>
     * Once a task is finished, it's current
     * state can not be changed back to {@code CurrentState.NOT_STARTED} or
     * {@code CurrentState.IN_PROGRESS}
     * </p>
     */
    public void completeTask(){
        System.out.println("Task finished!");
        currentState = CurrentState.FINISHED;
    }

    /**
     * A private method to ensure all Tasks created contain valid arguments.
     *
     * @param name the name of the task
     * @param details the details of the task
     * @param priority the priority level of the task
     * @return {@code true} if {@param name}, {@param details} and
     * {@param priority} are valid, otherwise return {@code false}
     */
    private boolean check(String name, String details, int priority){
        return !name.isEmpty() &&
                !details.isEmpty() &&
                (priority <= MAX_PRIORITY && priority >= 0);
    }

    //Overridden methods
    @Override
    public String toString() {
        return "%s:\nCurrent State: %s\n(%s)\n[PRIORITY = %d]\n".formatted(
                name, currentState, details, priority
        );
    }

    @Override
    public int compareTo(Task o) {
        int result1 = o.priority - priority;
        int result2 = currentState.ordinal() - o.currentState.ordinal();
        int result3 = name.compareTo(o.name);

        //Checks task's priority first, then current state and then the name.
        return (
                (result1 == 0) ? (
                        (result2 == 0) ? (result3) : result2)
                        : result1
                );
    }

    /**
     * A method to check if two tasks are equal or not.
     *
     * <p>
     * This method checks the name, priority and the current state of the
     * task to compare whether they are equal.
     * </p>
     * @param task the task to be checked
     * @return {@code true} if the tasks are equal, otherwise return {@code
     * false}
     */
    public boolean equals(Task task) {
        return this.name.equals(task.name) &&
                (this.priority == task.priority) &&
                (this.currentState == task.currentState);
    }

    //GETTERS AND SETTERS
    public CurrentState getCurrentState() {
        return currentState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (check(name, "default", 1)){
            this.name = name;
        } else throw new IllegalArgumentException("Name of task cannot be " +
                "empty!");
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        if (check("default", details, 1)){
            this.details = details;
        } else throw new IllegalArgumentException("Task details cannot be " +
                "empty!");
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (check("default", "default", priority)){
            this.priority = priority;
        } else throw new IllegalArgumentException("Priority of task must be " +
                "between 1 and 5");
    }
}
package com.taskmanager.core;

import com.taskmanager.util.IllegalCurrentStateException;

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
    private static final int MAX_PRIORITY = 5;
    private CurrentState currentState = CurrentState.NOT_STARTED;

    private String name;
    private String details;
    private int priority;

    //CONSTRUCTORS
    public Task(String name) {
        this(name, "None", 1);
    }

    public Task(String name, String details) {
        this(name, details, 1);
    }

    public Task(String name, int priority) {
        this(name, "None", 1);
    }

    public Task(String name, String details, int priority) {
        if (isValid(name, details, priority)){
            this.name = name;
            this.details = details;
            this.priority = priority;
        } else throw new IllegalArgumentException("Please check name, details "
                + "and/or priority of task!");
    }

    //TASK OPERATIONS

    /**
     * A method to start a task and set current state to {@code
     * CurrentState.IN_PROGRESS}.
     * <p>
     * Once a task is in progress, it's current
     * state can not be changed back to {@code CurrentState.NOT_STARTED}
     * </p>
     * @throws IllegalCurrentStateException if the current state is set to a
     * value lower than itself
     */
    public void startTask() throws IllegalCurrentStateException {
        setCurrentState(CurrentState.IN_PROGRESS);
    }

    /**
     * A method to complete a task and set current state to {@code
     * CurrentState.FINISHED}.
     * <p>
     * Once a task is finished, it's current
     * state can not be changed back to {@code CurrentState.NOT_STARTED} or
     * {@code CurrentState.IN_PROGRESS}
     * </p>
     * @throws IllegalCurrentStateException if the current state is set to a
     * value lower than itself
     */
    public void completeTask() throws IllegalCurrentStateException {
        setCurrentState(CurrentState.FINISHED);
    }

    //HELPER METHODS

    /**
     * A private method to ensure all Tasks created contain valid arguments.
     *
     * @param name the name of the task
     * @param details the details of the task
     * @param priority the priority level of the task
     * @return {@code true} if {@param name}, {@param details} and
     * {@param priority} are valid, otherwise return {@code false}
     */
    private boolean isValid(String name, String details, int priority){
        return !name.isEmpty() &&
                !details.isEmpty() &&
                (priority <= MAX_PRIORITY && priority >= 0);
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

    public boolean equalsIgnoreState(Task task){
        return this.name.equals(task.name) &&
                (this.priority == task.priority);
    }

    //GETTERS AND SETTERS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (isValid(name, "default", 1)){
            this.name = name;
        } else throw new IllegalArgumentException("Name of task cannot be " +
                "empty!");
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        if (isValid("default", details, 1)){
            this.details = details;
        } else throw new IllegalArgumentException("Task details cannot be " +
                "empty!");
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (isValid("default", "default", priority)){
            this.priority = priority;
        } else throw new IllegalArgumentException("Priority of task must be " +
                "between 1 and 5");
    }

    public CurrentState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(CurrentState currentState)
            throws IllegalCurrentStateException {
        int result = this.currentState.ordinal() - currentState.ordinal();
        if (result >= 0){
            throw new IllegalCurrentStateException("Current state cannot be " +
                    "set to a value lower than/equal to it's current value");
        }
        this.currentState = currentState;
    }

    //OVERRIDDEN METHODS
    @Override
    public String toString() {
        return "%s:\nCurrent State: %s\n(%s)\n[PRIORITY = %d]\n".formatted(
                name, currentState, details, priority
        );
    }


    @Override
    public int compareTo(Task o) {
        int result1 =  currentState.ordinal() - o.currentState.ordinal();
        int result2 = o.priority - priority;
        int result3 = name.compareTo(o.name);

        //Compare Task's current state, then priority and then the name.
        return ((result1 == 0) ?
                ((result2 == 0) ? (result3) : result2)
                : result1);
    }
}
package com.taskmanager.core;

import java.util.Comparator;

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

    public void startTask(){
        System.out.println("Task started!");
        currentState = CurrentState.IN_PROGRESS;
    }

    public void completeTask(){
        System.out.println("Task finished!");
        currentState = CurrentState.FINISHED;
    }

    /**
     * A private method to ensure all Tasks created contain valid arguments.
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

    //GETTERS AND SETTERS
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
package com.taskmanager.core;

import com.taskmanager.util.TaskSelector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The {@code TaskList} class represents a list of {@code Task} objects. The
 * class adds more functionality when dealing with a list of {@code Task}
 * objects.
 *
 * <p>
 * This class also contains methods to add/remove tasks, and print the
 * TaskList in a formatted manner. Further functionality is yet to be
 * implemented in future versions.
 * </p>
 *
 * @author mac
 * @see     Task
 * @since 1.0
 */
public class TaskList implements TaskSelector<TaskList> {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * A method to add a task to the {@code taskList}.
     * <p>
     * If the task is already in the {@code taskList}, the method will cause an
     * {@link IllegalArgumentException} to be thrown.
     * </p>
     * @param t the task to be added
     */
    public void addTask(Task t){
        if (taskList.isEmpty()){
            taskList.add(t);
        } else if (!containsTask(t)){
            taskList.add(t);
        } else throw new IllegalArgumentException("Task already in list!");
    }

    /**
     * A method to remove a task from the {@code taskList}.
     * <p>
     * If the task is already in the {@code taskList}, the method will remove
     * the task. Otherwise, it will cause a {@link NullPointerException} to
     * be thrown.
     * </p>
     * @param t the task to be removed
     * @throws NullPointerException if the task does not exist
     */
    public void removeTask(Task t) {
        Iterator<Task> iterator = taskList.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (t.equalsIgnoreState(task)) {
                iterator.remove();
                return;
            }
        }
        throw new NullPointerException("Task does not exist!");
    }

    /**
     * A method that formats the elements in {@code taskList} and prints them
     * in the console.
     */
    public void printList(){
        for (Task task : taskList){
            System.out.println("-".repeat(50));
            System.out.printf("""
                            |Task: %-42s|
                            |Current Progress: %-30s|
                            |Priority: %d %-36s|
                            |Details: %-39s|
                            %s
                            """,
                    task.getName(),
                    task.getCurrentState(),
                    task.getPriority(),
                    "",
                    task.getDetails(),
                    "-".repeat(50));
        }
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * A private method to check whether the {@code taskList} contains a task.
     *
     * <p>
     * It used the {@link Task#equals(Task task)} method to check whether the
     * tasks are equal or not.
     * </p>
     * @param t the task to be checked
     * @return {@code true} if {@code t} is present inside the {@code
     * taskList}, otherwise return {@code false}
     */
    private boolean containsTask(Task t){
        for (Task task : this.taskList){
            if (t.equals(task)){
                return true;
            }
        }
        return false;
    }

    /**
     * A method to return the next task with respect to priority, current 
     * state and name, in that order.
     * <p>
     * The method does not return a task whose {@code CurrentState} is {@code
     * CurrentState.FINISHED}. The {@code INDEX} field is incremented every 
     * time a {@code Task} is returned.
     * </p>
     * @return the Task that currently has the highest priority
     */
    @Override
    public Task getNextTask() {
        List<Task> copy = this.getTaskList();
        copy.sort(Task::compareTo);

        System.out.println(copy);
        Task nextTask = copy.getFirst();
        if ((nextTask != null) &&
                (nextTask.getCurrentState() != CurrentState.FINISHED)){
            return nextTask;
        } else {
            System.out.println("All tasks are finished!");
            return null;
        }
    }
}
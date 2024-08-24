package com.taskmanager.util;

import com.taskmanager.core.Task;
import com.taskmanager.core.TaskList;

/**
 * Represents an operation to select the next task from a {@code TaskList}.
 *
 * <p>
 * Task is selected based on priority, current state and name in that order.
 * This interface works for {@code TaskList} or any subclasses of {@code
 * TaskList}. This class utilizes the {@link Task#compareTo(Task)} method to
 * first sort the {@code TaskList} and return the element starting at index 0.
 * </p>
 * @param <T> the input type of the operation
 *
 * @author mac
 * @see     Task
 * @see     TaskList
 * @since 1.0
 */
@FunctionalInterface
public interface TaskSelector<T extends TaskList> {
    /**
     * A method to return the next task with respect to priority, current 
     * state and name, in that order.
     * 
     * @return the Task that currently has the highest priority
     */
    Task getNextTask();
}
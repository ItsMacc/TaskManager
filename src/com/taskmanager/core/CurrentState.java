package com.taskmanager.core;

/**
 * The {@code CurrentState} class represents the possible states of a task.
 * This class is used to differentiate tasks based on their current state,
 * and offer ways to manage tasks.
 *
 * <p>
 * There are three constants that can represent the state of a task:
 * <ul>
 *     <li> {@code NOT_STARTED}: Represents a task that has not yet been
 *     started. This is the default values of all {@code Task} objects when
 *     they are first created.
 *     </li>
 *     <li> {@code IN_PROGRESS}: Represents a task that has been started and
 *     is currently in progress. Any task that is this state cannot be
 *     changed back to {@code NOT_STARTED}.
 *     </li>
 *     <li> {@code FINISHED}: Represents a task that has been completed. Any
 *     task that is in this state cannot be changed back to {@code IN_PROGRESS}
 *     or {@code NOT_STARTED}.
 *     </li>
 * </ul>
 * </p>
 *
 * @author Mac
 * @see     Task
 * @since 1.0
 */
public enum CurrentState {
    NOT_STARTED,
    IN_PROGRESS,
    FINISHED;

    @Override
    public String toString() {
        switch (this){
            case NOT_STARTED -> {return "Not started yet!";}
            case IN_PROGRESS -> {return "Task is in progress...";}
            case FINISHED -> {return "Task completed successfully!";}
            default -> {return "Not started yet!";}
        }
    }
}

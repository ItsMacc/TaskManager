package com.taskmanager.util;

import com.taskmanager.core.CurrentState;

/**
 * Throws when an application attempts to change {@code CurrentState} to a
 * current state value lower than or equal to the present value. For example:
 * <ul>
 *     <li>{@code CurrentState.IN_PROGRESS} is set to {@code
 *     CurrentState.NOT_FINISHED}</li>
 *     <li>{@code CurrentState.FINISHED} is set to {@code
 *     CurrentState.IN_PROGRESS}</li>
 *     <li>{@code CurrentState.FINISHED} is set to {@code
 *     CurrentState.NOT_STARTED}</li>
 *     <li>{@code CurrentState.IN_PROGRESS} is set to {@code
 *     CurrentState.IN_PROGRESS}</li>
 *     <li>{@code CurrentState.FINISHED} is set to {@code
 *     CurrentState.IN_PROGRESS}</li>
 *     <li>{@code CurrentState.NOT_STARTED} is set to {@code
 *     CurrentState.NOT_STARTED}</li>
 * </ul>
 *
 * Applications should throw instances of this class to indicate other
 * illegal changing of {@code CurrentState}
 *
 * @author mac
 * @see     CurrentState
 * @since 1.0
 */
public class IllegalCurrentStateException extends Exception{
    public IllegalCurrentStateException(String message) {
        super(message);
    }
}
package com.taskmanager.core;

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

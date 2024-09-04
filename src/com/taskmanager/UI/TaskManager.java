package com.taskmanager.UI;

import com.taskmanager.core.Task;
import com.taskmanager.core.TaskList;
import com.taskmanager.util.IllegalCurrentStateException;
import java.util.Scanner;

public class TaskManager {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String intro = """
                Welcome to Task Manager! (CLI)
                Please select one of the following options:
                """;
    private static final String menu = """
            1. Add Task (a)
            2. Remove Task (r)
            3. Update Task Progress (u)
            4. Get Task (g)
            5. Show All Tasks (s/p)
            6. Quit (Q/Quit/q/quit)
            """;

    private static TaskList tasks = new TaskList();

    public static void main(String[] args) throws IllegalCurrentStateException {
        System.out.println(intro);
        displayMenu();

        String input = scanner.nextLine();

        while (!quit(input)) {
            switch (input.toLowerCase()) {
                case "a" -> {
                    addTaskToManager(tasks);
                    displayMenu();
                    input = scanner.nextLine();
                }
                case "r" -> {
                    removeTaskFromManager(tasks);
                    displayMenu();
                    input = scanner.nextLine();
                }
                case "u" -> {
                    updateTaskProgress(tasks);
                    displayMenu();
                    input = scanner.nextLine();
                }
                case "g" -> {
                    Task currentTask = tasks.getNextTask();
                    if (currentTask != null) {
                        System.out.println(currentTask);
                    }
                    displayMenu();
                    input = scanner.nextLine();
                }
                case "s", "p" -> {
                    tasks.printList();
                    displayMenu();
                    input = scanner.nextLine();
                }
                default -> {
                    System.out.println("Please enter a valid option\n");
                    displayMenu();
                    input = scanner.nextLine();
                }
            }
        }

        System.out.println("Thank you!");
    }

    private static void displayMenu() {
        System.out.print(menu + "Enter: ");
    }

    private static void  addTaskToManager(TaskList taskList) {
        Task task = createTask(true);

        System.out.printf("""

                Is this okay?
                Name: %s
                Priority: %d
                Details: %s
                """, task.getName(), task.getPriority(), task.getDetails());
        System.out.print("Y/N: ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("y")) {
            taskList.addTask(task);
            System.out.println("Task added!\n");
        } else {
             addTaskToManager(taskList);
        }
    }

    private static void updateTaskProgress(TaskList taskList)
            throws IllegalCurrentStateException {
        Task task = createTask();
        boolean found = false;

        for (Task t : taskList.getTaskList()){
            if (t.equalsIgnoreState(task)){
                System.out.println("Task found!");
                found = true;
                task = t;
                break;
            }
        }

        if (found) {
            System.out.println("Change the progress of the task:");
            System.out.print("Start task or finish task? (s/f): ");
            String input = scanner.nextLine();

            switch (input.toLowerCase()) {
                case "s" -> {
                    task.startTask();
                    System.out.println("Task started!");
                }
                case "f" -> {
                    task.completeTask();
                    System.out.println("Task finished!");
                }
                default -> {
                    System.out.println("Please enter either 's' (to start task) " +
                            "or 'f' (to finish task)");
                    updateTaskProgress(taskList);
                }
            }
        } else {
            System.out.println("Task not found! Please check values again");
        }
    }

    private static void  removeTaskFromManager(TaskList taskList){
        Task task = createTask();

        System.out.printf("""

                Removing task: %s
                Priority: %d
                Is this okay? (Y/N)
                """, task.getName(), task.getPriority());
        System.out.print("Y/N: ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("y")) {
            taskList.removeTask(task);
            System.out.println("Task removed!\n");
        } else {
             removeTaskFromManager(taskList);
        }
    }

    private static Task createTask() {
        return createTask(false);
    }
    
    private static Task createTask(boolean isAdding) {
        try {
            System.out.print("\nPlease enter task name: ");
            String name = scanner.nextLine();

            String details = "";
            if (isAdding) {
                System.out.print("Please enter any relevant details: ");
                details = scanner.nextLine();
            }

            System.out.print("Please enter the priority level of the task: ");
            String p = scanner.nextLine();

            if (name.isEmpty()) {
                System.out.println("Task name cannot be empty!");
                return createTask(true);
            }

            int priority = 1;
            if (!p.isEmpty()){
                priority = Integer.parseInt(p);
            }

            return new Task(name, details.isEmpty() ? "none" : details, priority);

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid priority");
            return createTask(isAdding);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createTask(isAdding);
        }
    }

    private static boolean quit(String input) {
        return input.equalsIgnoreCase("quit") ||
                input.equalsIgnoreCase("q");
    }
}

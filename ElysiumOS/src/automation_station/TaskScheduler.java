package automation_station;

import java.util.concurrent.*;
import java.util.*;

public class TaskScheduler {

    private final ScheduledExecutorService scheduler;
    private final Map<String, ScheduledFuture<?>> tasks;

    public TaskScheduler() {
        this.scheduler = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        this.tasks = new ConcurrentHashMap<>();
    }

    public void scheduleTask(String taskId, Runnable task, long delay, TimeUnit timeUnit) {
        ScheduledFuture<?> scheduledTask = scheduler.schedule(task, delay, timeUnit);
        tasks.put(taskId, scheduledTask);
        System.out.println("Task with ID: " + taskId + " has been scheduled.");
    }

    public void cancelTask(String taskId) {
        ScheduledFuture<?> scheduledTask = tasks.get(taskId);
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
            tasks.remove(taskId);
            System.out.println("Task with ID: " + taskId + " has been cancelled.");
        } else {
            System.out.println("Task with ID: " + taskId + " not found.");
        }
    }

    public void rescheduleTask(String taskId, Runnable task, long delay, TimeUnit timeUnit) {
        cancelTask(taskId);
        scheduleTask(taskId, task, delay, timeUnit);
    }

    public void shutdownScheduler() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
            System.out.println("Task Scheduler has been shut down.");
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public boolean isTaskScheduled(String taskId) {
        return tasks.containsKey(taskId) && !tasks.get(taskId).isDone();
    }

    // For demonstration purposes, here's a main method to show how the TaskScheduler might be used.
    public static void main(String[] args) {
        TaskScheduler taskScheduler = new TaskScheduler();

        Runnable task = () -> System.out.println("Executing task at " + new Date());
        String taskId = "uniqueTaskId";

        taskScheduler.scheduleTask(taskId, task, 5, TimeUnit.SECONDS);

        // Wait for a while to let the task execute
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Check if the task is still scheduled
        if (taskScheduler.isTaskScheduled(taskId)) {
            System.out.println("Task is still scheduled.");
        } else {
            System.out.println("Task has been executed or cancelled.");
        }

        // Shut down the scheduler
        taskScheduler.shutdownScheduler();
    }
}
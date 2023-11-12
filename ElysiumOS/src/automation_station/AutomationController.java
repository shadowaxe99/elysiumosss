package automation_station;

import utils.TaskQueue;
import utils.UtilityHelpers;
import networking.DataSync;
import java.util.List;

public class AutomationController {
    private TaskQueue taskQueue;
    private DataSync dataSync;
    private boolean isRunning;

    public AutomationController() {
        this.taskQueue = new TaskQueue();
        this.dataSync = new DataSync();
        this.isRunning = false;
    }

    public void startAutomation() {
        isRunning = true;
        while (isRunning) {
            if (!taskQueue.isEmpty()) {
                Runnable task = taskQueue.poll();
                executeTask(task);
            }
            UtilityHelpers.sleepThread(1000); // Sleep for a second before checking the queue again
        }
    }

    public void stopAutomation() {
        isRunning = false;
    }

    private void executeTask(Runnable task) {
        try {
            task.run();
            dataSync.syncData(); // Sync data after task execution
        } catch (Exception e) {
            UtilityHelpers.logError("AutomationController", "executeTask", e.getMessage());
        }
    }

    public void scheduleTask(Runnable task) {
        taskQueue.offer(task);
    }

    public List<Runnable> getPendingTasks() {
        return taskQueue.getTasks();
    }
}
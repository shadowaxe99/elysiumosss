package automation_station;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AutomationAPI provides an interface for creating, managing, and executing automation tasks.
 */
public class AutomationAPI {

    private Map<String, AutomationTask> taskMap;

    public AutomationAPI() {
        taskMap = new HashMap<>();
    }

    /**
     * Creates a new automation task.
     *
     * @param taskId   the unique identifier for the task
     * @param taskData the data required to create the task
     * @return boolean indicating success or failure of task creation
     */
    public boolean createTask(String taskId, Map<String, Object> taskData) {
        if (taskMap.containsKey(taskId)) {
            return false; // Task already exists
        }
        AutomationTask newTask = new AutomationTask(taskId, taskData);
        taskMap.put(taskId, newTask);
        return true;
    }

    /**
     * Executes a given automation task.
     *
     * @param taskId the unique identifier for the task
     * @return boolean indicating success or failure of task execution
     */
    public boolean executeTask(String taskId) {
        AutomationTask task = taskMap.get(taskId);
        if (task == null) {
            return false; // Task does not exist
        }
        return task.execute();
    }

    /**
     * Retrieves the status of a given task.
     *
     * @param taskId the unique identifier for the task
     * @return String representing the status of the task
     */
    public String getTaskStatus(String taskId) {
        AutomationTask task = taskMap.get(taskId);
        if (task == null) {
            return "Task not found";
        }
        return task.getStatus();
    }

    /**
     * Schedules a task to be executed at a later time.
     *
     * @param taskId   the unique identifier for the task
     * @param schedule the schedule details
     * @return boolean indicating success or failure of scheduling the task
     */
    public boolean scheduleTask(String taskId, TaskSchedule schedule) {
        AutomationTask task = taskMap.get(taskId);
        if (task == null) {
            return false; // Task does not exist
        }
        return task.schedule(schedule);
    }

    /**
     * Cancels a scheduled task.
     *
     * @param taskId the unique identifier for the task
     * @return boolean indicating success or failure of task cancellation
     */
    public boolean cancelTask(String taskId) {
        AutomationTask task = taskMap.get(taskId);
        if (task == null) {
            return false; // Task does not exist
        }
        return task.cancel();
    }

    /**
     * Lists all tasks.
     *
     * @return List of all task IDs
     */
    public List<String> listAllTasks() {
        return new ArrayList<>(taskMap.keySet());
    }

    /**
     * Deletes a task.
     *
     * @param taskId the unique identifier for the task
     * @return boolean indicating success or failure of task deletion
     */
    public boolean deleteTask(String taskId) {
        if (!taskMap.containsKey(taskId)) {
            return false; // Task does not exist
        }
        taskMap.remove(taskId);
        return true;
    }
}

class AutomationTask {
    private String taskId;
    private Map<String, Object> taskData;
    private String status;

    public AutomationTask(String taskId, Map<String, Object> taskData) {
        this.taskId = taskId;
        this.taskData = taskData;
        this.status = "Created";
    }

    public boolean execute() {
        // Implementation of task execution logic
        status = "Executed";
        return true;
    }

    public String getStatus() {
        return status;
    }

    public boolean schedule(TaskSchedule schedule) {
        // Implementation of task scheduling logic
        status = "Scheduled";
        return true;
    }

    public boolean cancel() {
        // Implementation of task cancellation logic
        status = "Cancelled";
        return true;
    }
}

class TaskSchedule {
    // Implementation of task scheduling details
}
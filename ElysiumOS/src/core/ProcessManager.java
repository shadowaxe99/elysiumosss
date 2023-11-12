package core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProcessManager {
    private final ConcurrentHashMap<String, Process> processMap;
    private final ExecutorService executorService;

    public ProcessManager() {
        processMap = new ConcurrentHashMap<>();
        executorService = Executors.newCachedThreadPool();
    }

    public void executeProcess(Runnable process, String processId) {
        processMap.put(processId, new Process(processId, process));
        executorService.execute(processMap.get(processId));
    }

    public void terminateProcess(String processId) {
        Process process = processMap.get(processId);
        if (process != null) {
            process.terminate();
            processMap.remove(processId);
        }
    }

    public void shutdown() {
        executorService.shutdownNow();
        processMap.forEach((id, process) -> process.terminate());
        processMap.clear();
    }

    private static class Process implements Runnable {
        private final String processId;
        private final Runnable target;
        private volatile boolean running = true;

        public Process(String processId, Runnable target) {
            this.processId = processId;
            this.target = target;
        }

        @Override
        public void run() {
            while (running) {
                try {
                    target.run();
                } catch (Exception e) {
                    System.err.println("Exception in process " + processId + ": " + e.getMessage());
                    terminate();
                }
            }
        }

        public void terminate() {
            running = false;
        }
    }
}
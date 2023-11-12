```java
package networking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import utils.UtilityHelpers;
import database.DatabaseManager;

public class DataSync {

    private ExecutorService executorService;
    private DatabaseManager databaseManager;

    public DataSync(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public Future<Boolean> syncUserData(String userId) {
        return executorService.submit(() -> {
            try {
                // Assume UtilityHelpers.serializeDataForSync() is a method that prepares user data for synchronization
                String serializedData = UtilityHelpers.serializeDataForSync(userId, databaseManager);
                // Assume UtilityHelpers.sendDataToCloud() is a method that sends data to a cloud service and returns success status
                return UtilityHelpers.sendDataToCloud(serializedData, cloudSyncStatus);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    public Future<Boolean> syncGameStates() {
        return executorService.submit(() -> {
            try {
                // Assume UtilityHelpers.serializeGameStateForSync() is a method that prepares game state data for synchronization
                String serializedData = UtilityHelpers.serializeGameStateForSync(gameState, databaseManager);
                // Assume UtilityHelpers.sendDataToCloud() is a method that sends data to a cloud service and returns success status
                return UtilityHelpers.sendDataToCloud(serializedData, cloudSyncStatus);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    public void shutdownSyncService() {
        executorService.shutdown();
    }
}
```
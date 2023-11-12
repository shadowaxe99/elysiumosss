```java
package cloud;

import utils.NetworkManager;
import utils.SecurityModule;
import database.DatabaseManager;

public class CloudSync {

    private NetworkManager networkManager;
    private SecurityModule securityModule;
    private DatabaseManager databaseManager;
    private boolean cloudSyncStatus;

    public CloudSync(NetworkManager networkManager, SecurityModule securityModule, DatabaseManager databaseManager) {
        this.networkManager = networkManager;
        this.securityModule = securityModule;
        this.databaseManager = databaseManager;
        this.cloudSyncStatus = false;
    }

    public void initiateSync() {
        if (networkManager.isConnected()) {
            try {
                securityModule.encryptData();
                String dataToSync = databaseManager.prepareDataForSync();
                networkManager.sendDataToCloud(dataToSync);
                cloudSyncStatus = true;
            } catch (Exception e) {
                cloudSyncStatus = false;
                e.printStackTrace();
            }
        } else {
            System.out.println("Network connection is unavailable. Cannot initiate cloud sync.");
        }
    }

    public boolean getSyncStatus() {
        return cloudSyncStatus;
    }

    public void setSyncStatus(boolean status) {
        this.cloudSyncStatus = status;
    }

    public void scheduleRegularSync(int intervalMinutes) {
        Runnable syncTask = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                initiateSync();
                try {
                    Thread.sleep(intervalMinutes * 60 * 1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
        new Thread(syncTask).start();
    }
}
```
```java
package elysiumos.src.recovery;

import elysiumos.src.utils.UtilityHelpers;
import elysiumos.src.database.DatabaseManager;
import java.io.IOException;
import java.nio.file.*;
import java.util.Date;

public class BackupManager {
    private final Path backupDirectory;
    private final DatabaseManager databaseManager;

    public BackupManager(Path backupDirectory, DatabaseManager databaseManager) {
        this.backupDirectory = backupDirectory;
        this.databaseManager = databaseManager;
    }

    public boolean createBackup(String backupName) {
        Path backupPath = backupDirectory.resolve(backupName + "_" + UtilityHelpers.formatDateForBackup(new Date()));
        try {
            Files.createDirectories(backupPath);
            databaseManager.exportData(backupPath);
            UtilityHelpers.logBackupEvent("Backup created: " + backupPath);
            return true;
        } catch (IOException e) {
            UtilityHelpers.logError("BackupManager", "createBackup", e.getMessage());
            return false;
        }
    }

    public boolean restoreBackup(String backupName) {
        Path backupPath = backupDirectory.resolve(backupName);
        if (Files.exists(backupPath)) {
            try {
                databaseManager.importData(backupPath);
                UtilityHelpers.logBackupEvent("Backup restored: " + backupPath);
                return true;
            } catch (IOException e) {
                UtilityHelpers.logError("BackupManager", "restoreBackup", e.getMessage());
                return false;
            }
        } else {
            UtilityHelpers.logError("BackupManager", "restoreBackup", "Backup not found: " + backupPath);
            return false;
        }
    }

    public boolean deleteBackup(String backupName) {
        Path backupPath = backupDirectory.resolve(backupName);
        try {
            UtilityHelpers.deleteDirectoryRecursively(backupPath);
            UtilityHelpers.logBackupEvent("Backup deleted: " + backupPath);
            return true;
        } catch (IOException e) {
            UtilityHelpers.logError("BackupManager", "deleteBackup", e.getMessage());
            return false;
        }
    }

    public void scheduleBackup(String backupName, long delayInMillis) {
        new Thread(() -> {
            try {
                Thread.sleep(delayInMillis);
                createBackup(backupName);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                UtilityHelpers.logError("BackupManager", "scheduleBackup", e.getMessage());
            }
        }).start();
    }
}
```
package recovery;

import utils.UtilityHelpers;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class RecoveryTool {

    private final Path backupDirectory;
    private final Path dataDirectory;

    public RecoveryTool(Path backupDirectory, Path dataDirectory) {
        this.backupDirectory = backupDirectory;
        this.dataDirectory = dataDirectory;
    }

    public boolean createBackup(String backupName) {
        Path backupPath = backupDirectory.resolve(backupName);
        try {
            UtilityHelpers.copyDirectory(dataDirectory, backupPath);
            return true;
        } catch (IOException e) {
            System.err.println("Backup creation failed: " + e.getMessage());
            return false;
        }
    }

    public boolean restoreBackup(String backupName) {
        Path backupPath = backupDirectory.resolve(backupName);
        if (Files.exists(backupPath)) {
            try {
                UtilityHelpers.copyDirectory(backupPath, dataDirectory, StandardCopyOption.REPLACE_EXISTING);
                return true;
            } catch (IOException e) {
                System.err.println("Backup restoration failed: " + e.getMessage());
                return false;
            }
        } else {
            System.err.println("Backup not found: " + backupName);
            return false;
        }
    }

    public boolean deleteBackup(String backupName) {
        Path backupPath = backupDirectory.resolve(backupName);
        try {
            UtilityHelpers.deleteDirectory(backupPath);
            return true;
        } catch (IOException e) {
            System.err.println("Backup deletion failed: " + e.getMessage());
            return false;
        }
    }

    // Utility class for common file operations
    public static class UtilityHelpers {

        public static void copyDirectory(Path source, Path target, StandardCopyOption... options) throws IOException {
            Files.walk(source).forEach(sourcePath -> {
                Path targetPath = target.resolve(source.relativize(sourcePath));
                try {
                    Files.copy(sourcePath, targetPath, options);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        public static void deleteDirectory(Path path) throws IOException {
            Files.walk(path)
                .sorted((path1, path2) -> path2.compareTo(path1)) // reverse; files before dirs
                .forEach(p -> {
                    try {
                        Files.delete(p);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        }
    }
}
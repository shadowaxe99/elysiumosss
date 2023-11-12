package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileExplorer {

    private Path currentDirectory;

    public FileExplorer() {
        this.currentDirectory = Paths.get(System.getProperty("user.dir"));
    }

    public void changeDirectory(String newDirectory) {
        Path newPath = currentDirectory.resolve(newDirectory);
        if (Files.exists(newPath) && Files.isDirectory(newPath)) {
            currentDirectory = newPath;
        } else {
            System.out.println("Directory does not exist.");
        }
    }

    public List<String> listFiles() {
        try (Stream<Path> files = Files.list(currentDirectory)) {
            return files.map(Path::toString).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createFile(String fileName) {
        Path newFilePath = currentDirectory.resolve(fileName);
        try {
            Files.createFile(newFilePath);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createDirectory(String dirName) {
        Path newDirPath = currentDirectory.resolve(dirName);
        try {
            Files.createDirectory(newDirPath);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteFile(String fileName) {
        Path filePath = currentDirectory.resolve(fileName);
        try {
            Files.delete(filePath);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getCurrentDirectory() {
        return currentDirectory.toString();
    }

    public boolean moveFile(String sourceFileName, String destinationFileName) {
        Path sourcePath = currentDirectory.resolve(sourceFileName);
        Path destinationPath = currentDirectory.resolve(destinationFileName);
        try {
            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean copyFile(String sourceFileName, String destinationFileName) {
        Path sourcePath = currentDirectory.resolve(sourceFileName);
        Path destinationPath = currentDirectory.resolve(destinationFileName);
        try {
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public long getFileSize(String fileName) {
        Path filePath = currentDirectory.resolve(fileName);
        try {
            return Files.size(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void printFileContents(String fileName) {
        Path filePath = currentDirectory.resolve(fileName);
        try {
            Files.lines(filePath).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
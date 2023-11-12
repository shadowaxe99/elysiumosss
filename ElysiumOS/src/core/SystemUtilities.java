package core;

import java.util.Properties;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SystemUtilities {

    private static final String CONFIG_FILE = "config.properties";

    public static Properties loadConfiguration() {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }

    public static void saveConfiguration(Properties prop) {
        try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static void updateSystemPreference(String key, String value) {
        Properties prop = loadConfiguration();
        prop.setProperty(key, value);
        saveConfiguration(prop);
    }

    public static String getSystemPreference(String key) {
        Properties prop = loadConfiguration();
        return prop.getProperty(key);
    }

    public static void logSystemEvent(String eventDescription) {
        // This method would contain logic to log system events for auditing and debugging
        // For example, it could write to a log file or a system monitoring service
    }

    public static void handleException(Exception e, String message) {
        // This method would contain logic to handle exceptions in a centralized manner
        // It could log the exception details and perform necessary cleanup operations
        logSystemEvent(message + ": " + e.getMessage());
    }

    // Additional utility methods can be added here to support system operations
}
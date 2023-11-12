package elysiumos.src.database;

import java.util.HashMap;
import java.util.Map;

/**
 * DataModeler is responsible for defining the structure of the data used within Elysium OS.
 * It provides the schema definitions for various entities such as User, AI_Agent, World, etc.
 */
public class DataModeler {

    public static Map<String, String> getUserSchema() {
        Map<String, String> schema = new HashMap<>();
        schema.put("id", "String");
        schema.put("username", "String");
        schema.put("email", "String");
        schema.put("passwordHash", "String");
        schema.put("userPreferences", "Map<String, Object>");
        schema.put("userSessionToken", "String");
        return schema;
    }

    public static Map<String, String> getAIAgentSchema() {
        Map<String, String> schema = new HashMap<>();
        schema.put("id", "String");
        schema.put("name", "String");
        schema.put("aiAgentState", "Map<String, Object>");
        return schema;
    }

    public static Map<String, String> getWorldSchema() {
        Map<String, String> schema = new HashMap<>();
        schema.put("id", "String");
        schema.put("ownerId", "String");
        schema.put("worldCustomizationSettings", "Map<String, Object>");
        return schema;
    }

    public static Map<String, String> getTransactionSchema() {
        Map<String, String> schema = new HashMap<>();
        schema.put("id", "String");
        schema.put("userId", "String");
        schema.put("nftOwnershipRecord", "String");
        schema.put("transactionAmount", "BigDecimal");
        schema.put("blockchainTransactionData", "Map<String, Object>");
        return schema;
    }

    public static Map<String, String> getEventSchema() {
        Map<String, String> schema = new HashMap<>();
        schema.put("id", "String");
        schema.put("name", "String");
        schema.put("description", "String");
        schema.put("startTime", "LocalDateTime");
        schema.put("endTime", "LocalDateTime");
        return schema;
    }

    public static Map<String, String> getTaskSchema() {
        Map<String, String> schema = new HashMap<>();
        schema.put("id", "String");
        schema.put("taskName", "String");
        schema.put("taskDescription", "String");
        schema.put("taskQueue", "Queue<Map<String, Object>>");
        return schema;
    }

    public static Map<String, String> getMatchSchema() {
        Map<String, String> schema = new HashMap<>();
        schema.put("id", "String");
        schema.put("players", "List<String>");
        schema.put("arenaMatchData", "Map<String, Object>");
        return schema;
    }

    public static Map<String, String> getItemSchema() {
        Map<String, String> schema = new HashMap<>();
        schema.put("id", "String");
        schema.put("name", "String");
        schema.put("description", "String");
        schema.put("price", "BigDecimal");
        return schema;
    }

    public static Map<String, String> getNFTSchema() {
        Map<String, String> schema = new HashMap<>();
        schema.put("id", "String");
        schema.put("ownerId", "String");
        schema.put("metadata", "Map<String, Object>");
        return schema;
    }

    public static Map<String, String> getDeviceSchema() {
        Map<String, String> schema = new HashMap<>();
        schema.put("id", "String");
        schema.put("type", "String");
        schema.put("deviceConnectionStatus", "Boolean");
        return schema;
    }

    public static Map<String, String> getPluginSchema() {
        Map<String, String> schema = new HashMap<>();
        schema.put("id", "String");
        schema.put("name", "String");
        schema.put("version", "String");
        schema.put("pluginConfigurations", "Map<String, Object>");
        return schema;
    }

    public static Map<String, String> getBackupSchema() {
        Map<String, String> schema = new HashMap<>();
        schema.put("id", "String");
        schema.put("userId", "String");
        schema.put("backupData", "byte[]");
        return schema;
    }

    public static Map<String, String> getCloudServiceSchema() {
        Map<String, String> schema = new HashMap<>();
        schema.put("id", "String");
        schema.put("serviceName", "String");
        schema.put("cloudSyncStatus", "Boolean");
        return schema;
    }

    // Additional schema definitions can be added here as needed for new data types.
}
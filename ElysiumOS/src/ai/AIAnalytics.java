package ai;

import java.util.HashMap;
import utils.UtilityHelpers;

public class AIAnalytics {

    private HashMap<String, Object> aiAgentState;
    private HashMap<String, Object> userPreferences;
    private HashMap<String, Object> gameState;
    private HashMap<String, Object> marketplaceListings;
    private HashMap<String, Object> virtualEnvironmentData;

    public AIAnalytics() {
        aiAgentState = new HashMap<>();
        userPreferences = new HashMap<>();
        gameState = new HashMap<>();
        marketplaceListings = new HashMap<>();
        virtualEnvironmentData = new HashMap<>();
    }

    public void updateAIState(String key, Object value) {
        aiAgentState.put(key, value);
    }

    public void updateUserPreferences(String key, Object value) {
        userPreferences.put(key, value);
    }

    public void updateGameState(String key, Object value) {
        gameState.put(key, value);
    }

    public void updateMarketplaceListings(String key, Object value) {
        marketplaceListings.put(key, value);
    }

    public void updateVirtualEnvironmentData(String key, Object value) {
        virtualEnvironmentData.put(key, value);
    }

    public HashMap<String, Object> getAIState() {
        return aiAgentState;
    }

    public HashMap<String, Object> getUserPreferences() {
        return userPreferences;
    }

    public HashMap<String, Object> getGameState() {
        return gameState;
    }

    public HashMap<String, Object> getMarketplaceListings() {
        return marketplaceListings;
    }

    public HashMap<String, Object> getVirtualEnvironmentData() {
        return virtualEnvironmentData;
    }

    public void logAnalyticsData() {
        // This method would contain logic to log analytics data to a persistent store
        // For demonstration purposes, we are printing the data to the console
        System.out.println("AI Agent State: " + UtilityHelpers.toJson(aiAgentState));
        System.out.println("User Preferences: " + UtilityHelpers.toJson(userPreferences));
        System.out.println("Game State: " + UtilityHelpers.toJson(gameState));
        System.out.println("Marketplace Listings: " + UtilityHelpers.toJson(marketplaceListings));
        System.out.println("Virtual Environment Data: " + UtilityHelpers.toJson(virtualEnvironmentData));
    }
}
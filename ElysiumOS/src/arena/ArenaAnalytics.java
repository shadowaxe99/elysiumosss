package arena;

import java.util.HashMap;
import java.util.Map;

/**
 * ArenaAnalytics is responsible for collecting and analyzing data from The Arena.
 * It provides insights into player performance, game statistics, and engagement metrics.
 */
public class ArenaAnalytics {

    private Map<String, Integer> playerPerformanceMetrics;
    private Map<String, Integer> gameStatistics;
    private Map<String, Double> engagementMetrics;

    public ArenaAnalytics() {
        playerPerformanceMetrics = new HashMap<>();
        gameStatistics = new HashMap<>();
        engagementMetrics = new HashMap<>();
    }

    /**
     * Records the performance of a player in a match.
     *
     * @param playerId The ID of the player.
     * @param score    The score achieved by the player.
     */
    public void recordPlayerPerformance(String playerId, int score) {
        playerPerformanceMetrics.put(playerId, score);
    }

    /**
     * Updates game statistics such as total matches played, average match duration, etc.
     *
     * @param statName  The name of the statistic.
     * @param statValue The value to be updated.
     */
    public void updateGameStatistics(String statName, int statValue) {
        gameStatistics.put(statName, statValue);
    }

    /**
     * Calculates engagement metrics like average session length, daily active users, etc.
     *
     * @param metricName  The name of the metric.
     * @param metricValue The value to be updated.
     */
    public void calculateEngagementMetrics(String metricName, double metricValue) {
        engagementMetrics.put(metricName, metricValue);
    }

    /**
     * Retrieves player performance metrics.
     *
     * @return A map of player IDs to their performance scores.
     */
    public Map<String, Integer> getPlayerPerformanceMetrics() {
        return playerPerformanceMetrics;
    }

    /**
     * Retrieves game statistics.
     *
     * @return A map of statistic names to their values.
     */
    public Map<String, Integer> getGameStatistics() {
        return gameStatistics;
    }

    /**
     * Retrieves engagement metrics.
     *
     * @return A map of metric names to their values.
     */
    public Map<String, Double> getEngagementMetrics() {
        return engagementMetrics;
    }

    /**
     * Generates a comprehensive report of all collected analytics.
     *
     * @return A string representation of the analytics report.
     */
    public String generateAnalyticsReport() {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Arena Analytics Report\n");
        reportBuilder.append("Player Performance Metrics:\n");
        playerPerformanceMetrics.forEach((playerId, score) ->
                reportBuilder.append("Player ID: ").append(playerId).append(", Score: ").append(score).append("\n"));

        reportBuilder.append("\nGame Statistics:\n");
        gameStatistics.forEach((statName, statValue) ->
                reportBuilder.append(statName).append(": ").append(statValue).append("\n"));

        reportBuilder.append("\nEngagement Metrics:\n");
        engagementMetrics.forEach((metricName, metricValue) ->
                reportBuilder.append(metricName).append(": ").append(metricValue).append("\n"));

        return reportBuilder.toString();
    }
}
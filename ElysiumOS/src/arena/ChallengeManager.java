package arena;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class ChallengeManager {
    private List<Match> activeMatches;
    private List<Match> completedMatches;

    public ChallengeManager() {
        activeMatches = new ArrayList<>();
        completedMatches = new ArrayList<>();
    }

    public Match createMatch(String gameType, List<String> playerIds) {
        Match match = new Match(UUID.randomUUID().toString(), gameType, playerIds);
        activeMatches.add(match);
        return match;
    }

    public void startMatch(String matchId) {
        Match match = findMatchById(matchId);
        if (match != null) {
            match.start();
            // Notify players that the match has started
            // This could be a WebSocket message or another form of communication
            sendMessageToPlayers(match.getPlayerIds(), "MatchInitiated", "The match has started!");
        }
    }

    public void endMatch(String matchId) {
        Match match = findMatchById(matchId);
        if (match != null) {
            match.end();
            activeMatches.remove(match);
            completedMatches.add(match);
            // Notify players that the match has ended
            sendMessageToPlayers(match.getPlayerIds(), "MatchEnded", "The match has ended!");
        }
    }

    private Match findMatchById(String matchId) {
        return activeMatches.stream()
                .filter(match -> match.getId().equals(matchId))
                .findFirst()
                .orElse(null);
    }

    private void sendMessageToPlayers(List<String> playerIds, String messageName, String message) {
        // Implementation for sending a message to players
        // This could be integrated with a messaging platform or a custom notification system
    }

    // Additional methods for managing challenges and matches could be added here
}

class Match {
    private String id;
    private String gameType;
    private List<String> playerIds;
    private boolean isStarted;
    private boolean isEnded;

    public Match(String id, String gameType, List<String> playerIds) {
        this.id = id;
        this.gameType = gameType;
        this.playerIds = playerIds;
        this.isStarted = false;
        this.isEnded = false;
    }

    public void start() {
        isStarted = true;
    }

    public void end() {
        isEnded = true;
    }

    public String getId() {
        return id;
    }

    public String getGameType() {
        return gameType;
    }

    public List<String> getPlayerIds() {
        return playerIds;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public boolean isEnded() {
        return isEnded;
    }
}
package metaverse;

import utils.UtilityHelpers;
import blockchain.TokenManager;
import blockchain.SmartContractHandler;
import database.DataModeler;

public class MetaverseAPI {

    private MetaverseManager metaverseManager;
    private AvatarCustomizer avatarCustomizer;
    private TokenManager tokenManager;
    private SmartContractHandler smartContractHandler;
    private DataModeler dataModeler;

    public MetaverseAPI() {
        metaverseManager = new MetaverseManager();
        avatarCustomizer = new AvatarCustomizer();
        tokenManager = new TokenManager();
        smartContractHandler = new SmartContractHandler();
        dataModeler = new DataModeler();
    }

    public String createVirtualEnvironment(String userId, String environmentSettings) {
        UtilityHelpers.logEvent("Creating virtual environment for user: " + userId);
        return metaverseManager.createEnvironment(userId, environmentSettings);
    }

    public boolean customizeAvatar(String userId, String customizationOptions) {
        UtilityHelpers.logEvent("Customizing avatar for user: " + userId);
        return avatarCustomizer.customize(userId, customizationOptions);
    }

    public String purchaseVirtualItem(String userId, String itemId, String paymentDetails) {
        UtilityHelpers.logEvent("Purchasing item for user: " + userId);
        if (tokenManager.validatePayment(paymentDetails)) {
            return smartContractHandler.processTransaction(userId, itemId, paymentDetails);
        } else {
            return "Payment validation failed";
        }
    }

    public String generateWorld(String userId, String worldPreferences) {
        UtilityHelpers.logEvent("Generating world for user: " + userId);
        return metaverseManager.generateWorld(userId, worldPreferences);
    }

    public boolean saveWorldState(String worldId, String worldState) {
        UtilityHelpers.logEvent("Saving world state for world: " + worldId);
        return dataModeler.updateWorldState(worldId, worldState);
    }

    public String loadWorldState(String worldId) {
        UtilityHelpers.logEvent("Loading world state for world: " + worldId);
        return dataModeler.getWorldState(worldId);
    }

    public boolean transferWorldOwnership(String worldId, String newOwnerId) {
        UtilityHelpers.logEvent("Transferring world ownership to new owner: " + newOwnerId);
        return smartContractHandler.transferOwnership(worldId, newOwnerId);
    }

    public String getWorldCustomizationSettings(String worldId) {
        UtilityHelpers.logEvent("Fetching world customization settings for world: " + worldId);
        return metaverseManager.getWorldCustomizationSettings(worldId);
    }

    public boolean updateWorldCustomizationSettings(String worldId, String newSettings) {
        UtilityHelpers.logEvent("Updating world customization settings for world: " + worldId);
        return metaverseManager.updateWorldCustomizationSettings(worldId, newSettings);
    }
}
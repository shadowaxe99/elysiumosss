package core;

import utils.SecurityModule;
import networking.NetworkController;
import database.DatabaseManager;
import blockchain.BlockchainManager;
import ai.NaturalLanguageProcessor;
import user_interface.UIManager;

public class Kernel {
    private static Kernel instance;
    private final SecurityModule securityModule;
    private final NetworkController networkController;
    private final DatabaseManager databaseManager;
    private final BlockchainManager blockchainManager;
    private final NaturalLanguageProcessor naturalLanguageProcessor;
    private final UIManager uiManager;

    private Kernel() {
        securityModule = new SecurityModule();
        networkController = new NetworkController();
        databaseManager = new DatabaseManager();
        blockchainManager = new BlockchainManager();
        naturalLanguageProcessor = new NaturalLanguageProcessor();
        uiManager = new UIManager();
    }

    public static synchronized Kernel getInstance() {
        if (instance == null) {
            instance = new Kernel();
        }
        return instance;
    }

    public void initializeSystem() {
        securityModule.initialize();
        networkController.initialize();
        databaseManager.initialize();
        blockchainManager.initialize();
        naturalLanguageProcessor.initialize();
        uiManager.initialize();
    }

    public SecurityModule getSecurityModule() {
        return securityModule;
    }

    public NetworkController getNetworkController() {
        return networkController;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public BlockchainManager getBlockchainManager() {
        return blockchainManager;
    }

    public NaturalLanguageProcessor getNaturalLanguageProcessor() {
        return naturalLanguageProcessor;
    }

    public UIManager getUiManager() {
        return uiManager;
    }

    public void shutdownSystem() {
        uiManager.shutdown();
        naturalLanguageProcessor.shutdown();
        blockchainManager.shutdown();
        databaseManager.shutdown();
        networkController.shutdown();
        securityModule.shutdown();
    }
}
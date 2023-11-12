package user_interface;

import java.awt.*;
import javax.swing.*;
import core.SystemUtilities;
import utils.UtilityHelpers;
import utils.FileExplorer;
import utils.SecurityModule;

public class UIManager {
    private JFrame mainFrame;
    private JMenuBar menuBar;
    private JToolBar toolBar;
    private MainUI mainUI;
    private Dashboard dashboard;
    private SettingsPanel settingsPanel;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public UIManager() {
        initializeComponents();
        layoutComponents();
        registerEventHandlers();
    }

    private void initializeComponents() {
        mainFrame = new JFrame("Elysium OS");
        menuBar = new JMenuBar();
        toolBar = new JToolBar();
        mainUI = new MainUI();
        dashboard = new Dashboard();
        settingsPanel = new SettingsPanel();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
    }

    private void layoutComponents() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);

        setupMenuBar();
        setupToolBar();

        cardPanel.add(mainUI, "MainUI");
        cardPanel.add(dashboard, "Dashboard");
        cardPanel.add(settingsPanel, "SettingsPanel");

        mainFrame.add(toolBar, BorderLayout.PAGE_START);
        mainFrame.add(cardPanel, BorderLayout.CENTER);
    }

    private void setupMenuBar() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> SystemUtilities.exitApplication());
        fileMenu.add(exitItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> UtilityHelpers.showAboutInfo());

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        mainFrame.setJMenuBar(menuBar);
    }

    private void setupToolBar() {
        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(e -> cardLayout.show(cardPanel, "MainUI"));
        toolBar.add(homeButton);

        JButton dashboardButton = new JButton("Dashboard");
        dashboardButton.addActionListener(e -> cardLayout.show(cardPanel, "Dashboard"));
        toolBar.add(dashboardButton);

        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(e -> cardLayout.show(cardPanel, "SettingsPanel"));
        toolBar.add(settingsButton);
    }

    private void registerEventHandlers() {
        // Event handlers for UI interactions can be registered here
        // Example: mainUI.onUserLogin(this::handleUserLogin);
    }

    public void showUI() {
        SwingUtilities.invokeLater(() -> mainFrame.setVisible(true));
    }

    // Additional methods to handle UI logic, such as switching views, updating components, etc.
    // Example: private void handleUserLogin(String username, String password) { ... }
}

// Note: This code assumes the existence of other classes like MainUI, Dashboard, SettingsPanel, etc.
// and utility methods from SystemUtilities and UtilityHelpers.
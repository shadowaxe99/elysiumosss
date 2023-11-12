package elysiumos.src.accessibility;

import elysiumos.src.utils.UtilityHelpers;

/**
 * AccessibilityFeatures class provides various accessibility options to enhance the user experience
 * for individuals with disabilities. It includes features like text-to-speech, high-contrast themes,
 * and assistive navigation aids.
 */
public class AccessibilityFeatures {

    private boolean textToSpeechEnabled;
    private boolean highContrastThemeEnabled;
    private boolean navigationAidsEnabled;
    
    // Constructor
    public AccessibilityFeatures() {
        // Load user preferences or set defaults
        this.textToSpeechEnabled = UtilityHelpers.getUserPreference("textToSpeechEnabled", false);
        this.highContrastThemeEnabled = UtilityHelpers.getUserPreference("highContrastThemeEnabled", false);
        this.navigationAidsEnabled = UtilityHelpers.getUserPreference("navigationAidsEnabled", false);
    }
    
    // Enable or disable text-to-speech
    public void setTextToSpeechEnabled(boolean enabled) {
        this.textToSpeechEnabled = enabled;
        UtilityHelpers.updateUserPreferences("textToSpeechEnabled", enabled);
    }
    
    // Check if text-to-speech is enabled
    public boolean isTextToSpeechEnabled() {
        return textToSpeechEnabled;
    }
    
    // Enable or disable high-contrast theme
    public void setHighContrastThemeEnabled(boolean enabled) {
        this.highContrastThemeEnabled = enabled;
        UtilityHelpers.updateUserPreferences("highContrastThemeEnabled", enabled);
    }
    
    // Check if high-contrast theme is enabled
    public boolean isHighContrastThemeEnabled() {
        return highContrastThemeEnabled;
    }
    
    // Enable or disable navigation aids
    public void setNavigationAidsEnabled(boolean enabled) {
        this.navigationAidsEnabled = enabled;
        UtilityHelpers.updateUserPreferences("navigationAidsEnabled", enabled);
    }
    
    // Check if navigation aids are enabled
    public boolean isNavigationAidsEnabled() {
        return navigationAidsEnabled;
    }
    
    // Apply accessibility settings to the UI
    public void applySettings() {
        if (textToSpeechEnabled) {
            // Code to enable text-to-speech functionality
        }
        
        if (highContrastThemeEnabled) {
            // Code to apply high-contrast theme
        }
        
        if (navigationAidsEnabled) {
            // Code to enable navigation aids
        }
    }
    
    // Reset accessibility settings to default
    public void resetToDefaults() {
        setTextToSpeechEnabled(false);
        setHighContrastThemeEnabled(false);
        setNavigationAidsEnabled(false);
        applySettings();
    }
}
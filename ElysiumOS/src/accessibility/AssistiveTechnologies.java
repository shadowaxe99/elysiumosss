```java
package elysiumos.src.accessibility;

import elysiumos.src.utils.UtilityHelpers;

/**
 * AssistiveTechnologies provides accessibility features to enhance the user experience
 * for individuals with disabilities. It integrates with the system to offer various
 * assistive options like screen readers, voice control, and more.
 */
public class AssistiveTechnologies {

    private boolean screenReaderEnabled;
    private boolean voiceControlEnabled;
    private boolean highContrastModeEnabled;
    private boolean textToSpeechEnabled;

    public AssistiveTechnologies() {
        // Load user preferences for accessibility options
        this.screenReaderEnabled = UtilityHelpers.getAccessibilityOption("screenReader");
        this.voiceControlEnabled = UtilityHelpers.getAccessibilityOption("voiceControl");
        this.highContrastModeEnabled = UtilityHelpers.getAccessibilityOption("highContrast");
        this.textToSpeechEnabled = UtilityHelpers.getAccessibilityOption("textToSpeech");
    }

    public void toggleScreenReader(boolean enabled) {
        this.screenReaderEnabled = enabled;
        // Implement logic to enable or disable the screen reader
    }

    public void toggleVoiceControl(boolean enabled) {
        this.voiceControlEnabled = enabled;
        // Implement logic to enable or disable voice control
    }

    public void toggleHighContrastMode(boolean enabled) {
        this.highContrastModeEnabled = enabled;
        // Implement logic to enable or disable high contrast mode
    }

    public void toggleTextToSpeech(boolean enabled) {
        this.textToSpeechEnabled = enabled;
        // Implement logic to enable or disable text to speech
    }

    // Getters and Setters
    public boolean isScreenReaderEnabled() {
        return screenReaderEnabled;
    }

    public boolean isVoiceControlEnabled() {
        return voiceControlEnabled;
    }

    public boolean isHighContrastModeEnabled() {
        return highContrastModeEnabled;
    }

    public boolean isTextToSpeechEnabled() {
        return textToSpeechEnabled;
    }
}
```
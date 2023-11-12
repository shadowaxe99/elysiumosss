```java
package elysiumos.src.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
    private Locale currentLocale;
    private ResourceBundle messages;

    public LanguageManager() {
        // Default locale is English
        this.currentLocale = new Locale("en", "US");
        loadResourceBundle();
    }

    public void changeLanguage(String languageCode, String countryCode) {
        this.currentLocale = new Locale(languageCode, countryCode);
        loadResourceBundle();
    }

    private void loadResourceBundle() {
        messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    }

    public String getString(String key) {
        return messages.getString(key);
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    // This method can be used to dynamically update language settings across the platform
    public void updateLanguageSettings() {
        // Broadcast the language change event to all components that need to update their language settings
        // For example, the UI components can listen to this event and update the displayed texts accordingly
        // EventBus.publish(new LanguageChangeEvent(this.currentLocale));
    }
}
```
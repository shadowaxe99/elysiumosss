package localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class RegionalSettings {
    private Locale currentLocale;
    private ResourceBundle messages;

    public RegionalSettings() {
        // Default locale is English, United States
        this.currentLocale = new Locale("en", "US");
        loadResourceBundle();
    }

    public void setLocale(String languageCode, String countryCode) {
        this.currentLocale = new Locale(languageCode, countryCode);
        loadResourceBundle();
    }

    private void loadResourceBundle() {
        messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    }

    public String getMessage(String key) {
        return messages.getString(key);
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    // Change the language settings for the user interface
    public void changeLanguage(String languageCode, String countryCode) {
        setLocale(languageCode, countryCode);
        // Notify the system of the locale change
        notifyLocaleChange();
    }

    private void notifyLocaleChange() {
        // This method would notify other components of the system about the locale change
        // For example, it could update the UI elements with the new language settings
        // This is a placeholder for the actual implementation
    }
}
package main.constants;

import java.util.Locale;

public enum LanguageConstants {
    English("EN"), French("FR");

    private final String lang;

    LanguageConstants(String lang) { this.lang = lang; }

    public String getLang() { return lang; }

    public static LanguageConstants getLangItem(String lang) {
        for (LanguageConstants constant : LanguageConstants.values()) {
            if (constant.getLang().equalsIgnoreCase(lang)) {
                return constant;
            }
        }
        return English;
    }

    public static Locale getLocale(LanguageConstants languageConstants) {
        // Retourne l'objet Locale correspondant à la langue
        switch (languageConstants) {
            case French:
                return Locale.FRENCH;  // Locale pour la langue française
            case English:
            default:
                return Locale.ENGLISH;  // Locale pour la langue anglaise
        }
    }
}

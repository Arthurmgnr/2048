package main.constants;

import java.util.Locale;

/**
 * Permet de recuperer toutes les langues disponibles pour permettre au joueur de choisir celle qu'il souhaite
 */
public enum LanguageConstants {
    English("EN"), French("FR");

    private final String lang;

    LanguageConstants(String lang) { this.lang = lang; }

    public String getLang() { return lang; }

    // Permet de recuperer la langue selon la chaine de caractere "FR" ou "EN"
    public static LanguageConstants getLangItem(String lang) {
        for (LanguageConstants constant : LanguageConstants.values()) {
            if (constant.getLang().equalsIgnoreCase(lang)) {
                return constant;
            }
        }
        return English;
    }

    // Permet de recuperer la locale associee a la constante passee en parametre
    public static Locale getLocale(LanguageConstants languageConstants) {
        // Retourne l'objet Locale correspondant à la langue
        switch (languageConstants) {
            case French:
                return Locale.FRENCH;  // Locale pour la langue francaise
            case English:
            default:
                return Locale.ENGLISH;  // Locale pour la langue anglaise
        }
    }
}

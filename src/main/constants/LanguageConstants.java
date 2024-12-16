package main.constants;

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
}

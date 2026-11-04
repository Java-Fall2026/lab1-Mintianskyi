package ua.itsupport.util;

class FormatHelper {

    private FormatHelper() {
    }

    static String normalizeTrim(String str) {
        return str == null ? null : str.trim();
    }

    static String normalizeLower(String str) {
        return str == null ? null : str.trim().toLowerCase();
    }

    static String normalizeUpper(String str) {
        return str == null ? null : str.trim().toUpperCase();
    }

    static String capitalize(String str) {
        if (str == null || str.isBlank()) {
            return str;
        }
        String trimmed = str.trim();
        return Character.toUpperCase(trimmed.charAt(0)) + trimmed.substring(1).toLowerCase();
    }

    static String formatMoney(double amount) {
        return String.format("%.2f грн", amount);
    }
}
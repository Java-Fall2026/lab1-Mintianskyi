package ua.itsupport.util;

class FormatHelper {
    private FormatHelper() {}

    static String normalizeString(String value) {
        return value == null ? null : value.trim();
    }

    static String normalizeEmail(String value) {
        return value == null ? null : value.trim().toLowerCase();
    }

    static String normalizeEnum(String value, String[] allowed) {
        if (value == null) return null;
        String upper = value.trim().toUpperCase();
        for (String item : allowed) {
            if (item.equals(upper)) {
                return upper;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value + ". Allowed: " + String.join(", ", allowed));
    }
}
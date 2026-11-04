package ua.itsupport.util;

import java.time.LocalDate;
import java.util.Collection;

class ValidationHelper {

    private ValidationHelper() {
    }

    static <T> T requireNotNull(T value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " must not be null");
        }
        return value;
    }

    static String requireNotBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
        return value;
    }

    static int requireInRange(int value, int min, int max, String fieldName) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(
                String.format("%s must be between %d and %d, got: %d", fieldName, min, max, value)
            );
        }
        return value;
    }

    static double requireInRange(double value, double min, double max, String fieldName) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(
                String.format("%s must be between %.2f and %.2f, got: %.2f", fieldName, min, max, value)
            );
        }
        return value;
    }

    static LocalDate requireNotFuture(LocalDate date, String fieldName) {
        requireNotNull(date, fieldName);
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                String.format("%s must not be in the future, got: %s", fieldName, date)
            );
        }
        return date;
    }

    static LocalDate requireOnOrAfter(LocalDate date, LocalDate baseDate, String fieldName, String baseFieldName) {
        if (date != null && date.isBefore(baseDate)) {
            throw new IllegalArgumentException(
                String.format("%s (%s) must be on or after %s (%s)", fieldName, date, baseFieldName, baseDate)
            );
        }
        return date;
    }

    static String requireInAllowedValues(String value, Collection<String> allowed, String fieldName) {
        requireNotBlank(value, fieldName);
        if (!allowed.contains(value)) {
            throw new IllegalArgumentException(
                String.format("%s must be one of %s, got: '%s'", fieldName, allowed, value)
            );
        }
        return value;
    }
}
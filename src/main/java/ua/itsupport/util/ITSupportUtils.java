package ua.itsupport.util;

import ua.itsupport.model.ITTicket;
import ua.itsupport.model.WorkLog;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

public class ITSupportUtils {

    static final List<String> VALID_PRIORITIES = List.of("LOW", "MEDIUM", "HIGH", "CRITICAL");
    static final List<String> VALID_STATES = List.of("OPEN", "IN_PROGRESS", "RESOLVED", "CLOSED");

    private static final Map<Integer, Double> HOURLY_RATES = Map.of(
        1, 15.0,
        2, 25.0,
        3, 40.0,
        4, 60.0,
        5, 85.0
    );

    private ITSupportUtils() {
    }

    public static String cleanAndLower(String value, String fieldName) {
        ValidationHelper.requireNotBlank(value, fieldName);
        return FormatHelper.normalizeLower(value);
    }

    public static String cleanNotBlank(String value, String fieldName) {
        ValidationHelper.requireNotBlank(value, fieldName);
        return FormatHelper.normalizeTrim(value);
    }

    public static String cleanPriority(String priority) {
        ValidationHelper.requireNotBlank(priority, "Priority");
        String normalized = FormatHelper.normalizeUpper(priority);
        return ValidationHelper.requireInAllowedValues(normalized, VALID_PRIORITIES, "Priority");
    }

    public static String cleanState(String state) {
        ValidationHelper.requireNotBlank(state, "State");
        String normalized = FormatHelper.normalizeUpper(state);
        return ValidationHelper.requireInAllowedValues(normalized, VALID_STATES, "State");
    }

    public static <T> T checkNotNull(T value, String fieldName) {
        return ValidationHelper.requireNotNull(value, fieldName);
    }

    public static int checkSkillLevel(int level) {
        return ValidationHelper.requireInRange(level, 1, 5, "Skill level");
    }

    public static double checkHoursSpent(double hours) {
        return ValidationHelper.requireInRange(hours, 0.25, 24.0, "Hours spent");
    }

    public static double validateHoursSpent(double hours) {
        return checkHoursSpent(hours);
    }

    public static LocalDate checkCreatedDate(LocalDate date) {
        return ValidationHelper.requireNotFuture(date, "Created date");
    }

    public static LocalDate checkResolvedDate(LocalDate resolvedDate, LocalDate createdDate) {
        if (resolvedDate == null) {
            return null;
        }
        return ValidationHelper.requireOnOrAfter(resolvedDate, createdDate, "Resolved date", "Created date");
    }

    public static LocalDate checkLogDate(LocalDate logDate, LocalDate ticketCreatedDate) {
        ValidationHelper.requireNotNull(logDate, "Log date");
        return ValidationHelper.requireOnOrAfter(logDate, ticketCreatedDate, "Log date", "Ticket created date");
    }

    public static String capitalizeText(String text) {
        return FormatHelper.capitalize(text);
    }

    public static String formatMoneyValue(double amount) {
        return FormatHelper.formatMoney(amount);
    }

    public static long resolutionDays(ITTicket ticket) {
        ValidationHelper.requireNotNull(ticket, "Ticket");
        if (ticket.getResolvedDate() == null) {
            return -1;
        }
        return ChronoUnit.DAYS.between(ticket.getCreatedDate(), ticket.getResolvedDate());
    }

    public static double workCost(WorkLog log) {
        ValidationHelper.requireNotNull(log, "WorkLog");
        int skill = log.getTechnician().getSkillLevel();
        double rate = HOURLY_RATES.getOrDefault(skill, 15.0);
        return log.getHoursSpent() * rate;
    }
}
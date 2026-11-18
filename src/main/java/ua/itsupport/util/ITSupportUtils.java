package ua.itsupport.util;

import ua.itsupport.model.ITTicket;
import ua.itsupport.model.Priority;
import ua.itsupport.model.TicketState;
import ua.itsupport.model.WorkLog;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public class ITSupportUtils {

    private static final Map<Integer, Double> HOURLY_RATES = Map.of(
        1, 15.0,
        2, 25.0,
        3, 40.0,
        4, 60.0,
        5, 85.0
    );

    private ITSupportUtils() {}

    public static String cleanAndLower(String value, String fieldName) {
        ValidationHelper.requireNotBlank(value, fieldName);
        return FormatHelper.normalizeLower(value);
    }

    public static String cleanNotBlank(String value, String fieldName) {
        ValidationHelper.requireNotBlank(value, fieldName);
        return FormatHelper.normalizeTrim(value);
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

    public static String getPriorityDescription(Priority priority) {
        return switch (priority) {
            case LOW -> "Низький пріоритет (планова задача)";
            case MEDIUM -> "Середній пріоритет (стандартна заява)";
            case HIGH -> "Високий пріоритет (терміново)";
            case CRITICAL -> "Критичний пріоритет (аварія системи)";
        };
    }

    public static String getActionDescriptionByState(TicketState state) {
        return switch (state) {
            case OPEN -> "Заявка зареєстрована та очікує призначення.";
            case IN_PROGRESS -> "Технік працює над вирішенням проблеми.";
            case RESOLVED -> "Проблему вирішено, очікує підтвердження.";
            case CLOSED -> "Заявка закрита.";
        };
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
        int skill = log.technician().getSkillLevel();
        double rate = HOURLY_RATES.getOrDefault(skill, 15.0);
        double priorityMultiplier = log.ticket().getPriority().getWeightFactor();
        return log.hoursSpent() * rate * priorityMultiplier;
    }
}
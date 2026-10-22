package ua.itsupport.util;

import ua.itsupport.model.ITTicket;
import ua.itsupport.model.WorkLog;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public class ITSupportUtils {
    public static final String[] VALID_PRIORITIES = {"LOW", "MEDIUM", "HIGH", "CRITICAL"};
    public static final String[] VALID_STATES = {"OPEN", "IN_PROGRESS", "RESOLVED", "CLOSED"};

    private static final Map<Integer, Double> HOURLY_RATES = Map.of(
        1, 15.0, 2, 25.0, 3, 40.0, 4, 60.0, 5, 90.0
    );

    private ITSupportUtils() {}

    public static String validateAndNormalizeEmail(String email) {
        ValidationHelper.requireNotBlank(email, "Email");
        return FormatHelper.normalizeEmail(email);
    }

    public static String validateAndNormalizeString(String value, String fieldName) {
        ValidationHelper.requireNotBlank(value, fieldName);
        return FormatHelper.normalizeString(value);
    }

    public static int validateSkillLevel(int level) {
        ValidationHelper.requireInRange(level, 1, 5, "Skill level");
        return level;
    }

    public static String validateAndNormalizePriority(String priority) {
        return FormatHelper.normalizeEnum(priority, VALID_PRIORITIES);
    }

    public static String validateAndNormalizeState(String state) {
        return FormatHelper.normalizeEnum(state, VALID_STATES);
    }

    public static double validateHoursSpent(double hours) {
        ValidationHelper.requireInRange(hours, 0.25, 24.0, "Hours spent");
        return hours;
    }

    public static long resolutionDays(ITTicket ticket) {
        ValidationHelper.requireNotNull(ticket, "Ticket");
        if (ticket.getResolvedDate() == null) {
            return -1;
        }
        return ChronoUnit.DAYS.between(ticket.getCreatedDate(), ticket.getResolvedDate());
    }

    public static double workCost(WorkLog workLog) {
        ValidationHelper.requireNotNull(workLog, "WorkLog");
        int level = workLog.getTechnician().getSkillLevel();
        double rate = HOURLY_RATES.getOrDefault(level, 20.0);
        return workLog.getHoursSpent() * rate;
    }
}
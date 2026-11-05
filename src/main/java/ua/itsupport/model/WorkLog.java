package ua.itsupport.model;

import ua.itsupport.util.ITSupportUtils;
import java.time.LocalDate;

public record WorkLog(
        ITTicket ticket,
        Technician technician,
        LocalDate logDate,
        double hoursSpent
) {
    public WorkLog {
        ITSupportUtils.checkNotNull(ticket, "Ticket");
        ITSupportUtils.checkNotNull(technician, "Technician");
        ITSupportUtils.checkLogDate(logDate, ticket.createdDate());
        ITSupportUtils.checkHoursSpent(hoursSpent);
    }
}
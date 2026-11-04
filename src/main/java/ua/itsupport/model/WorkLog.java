package ua.itsupport.model;

import ua.common.BaseEntity;
import ua.itsupport.util.ITSupportUtils;

import java.time.LocalDate;
import java.util.Objects;

public class WorkLog extends BaseEntity {

    private final ITTicket ticket;
    private final Technician technician;
    private final LocalDate logDate;
    private final double hoursSpent;

    private WorkLog(ITTicket ticket, Technician technician, LocalDate logDate, double hoursSpent) {
        super();
        this.ticket = ITSupportUtils.checkNotNull(ticket, "Ticket");
        this.technician = ITSupportUtils.checkNotNull(technician, "Technician");
        this.logDate = ITSupportUtils.checkLogDate(logDate, ticket.getCreatedDate());
        this.hoursSpent = ITSupportUtils.checkHoursSpent(hoursSpent);
    }

    public static WorkLog of(ITTicket ticket, Technician technician, LocalDate logDate, double hoursSpent) {
        return new WorkLog(ticket, technician, logDate, hoursSpent);
    }

    public ITTicket getTicket() {
        return ticket;
    }

    public Technician getTechnician() {
        return technician;
    }

    public LocalDate getLogDate() {
        return logDate;
    }

    public double getHoursSpent() {
        return hoursSpent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkLog workLog = (WorkLog) o;
        return Objects.equals(ticket, workLog.ticket) &&
               Objects.equals(technician, workLog.technician) &&
               Objects.equals(logDate, workLog.logDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticket, technician, logDate);
    }

    @Override
    public String toString() {
        return "WorkLog{" +
                "ticket=" + ticket +
                ", technician=" + technician +
                ", logDate=" + logDate +
                ", hoursSpent=" + hoursSpent +
                ", createdAt=" + createdAt +
                '}';
    }
}
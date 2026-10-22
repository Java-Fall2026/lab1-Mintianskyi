package ua.itsupport.model;

import ua.common.BaseEntity;
import ua.itsupport.util.ITSupportUtils;
import java.time.LocalDate;
import java.util.Objects;

public class WorkLog extends BaseEntity {
    private ITTicket ticket;
    private Technician technician;
    private LocalDate logDate;
    private double hoursSpent;

    private WorkLog(ITTicket ticket, Technician technician, LocalDate logDate, double hoursSpent) {
        super();
        setTicket(ticket);
        setTechnician(technician);
        setLogDate(logDate);
        setHoursSpent(hoursSpent);
    }

    public static WorkLog of(ITTicket ticket, Technician technician, LocalDate logDate, double hoursSpent) {
        return new WorkLog(ticket, technician, logDate, hoursSpent);
    }

    public ITTicket getTicket() {
        return ticket;
    }

    public void setTicket(ITTicket ticket) {
        if (ticket == null) throw new IllegalArgumentException("Ticket must not be null");
        this.ticket = ticket;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        if (technician == null) throw new IllegalArgumentException("Technician must not be null");
        this.technician = technician;
    }

    public LocalDate getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDate logDate) {
        if (logDate == null) throw new IllegalArgumentException("Log date must not be null");
        if (ticket != null && ticket.getCreatedDate() != null && logDate.isBefore(ticket.getCreatedDate())) {
            throw new IllegalArgumentException("Log date must be >= ticket createdDate, got: " + logDate);
        }
        this.logDate = logDate;
    }

    public double getHoursSpent() {
        return hoursSpent;
    }

    public void setHoursSpent(double hoursSpent) {
        this.hoursSpent = ITSupportUtils.validateHoursSpent(hoursSpent);
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
        return "WorkLog{createdAt=" + createdAt + ", logDate=" + logDate + ", hoursSpent=" + hoursSpent + "}";
    }
}
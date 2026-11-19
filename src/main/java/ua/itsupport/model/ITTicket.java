package ua.itsupport.model;

import ua.itsupport.util.ITSupportUtils;
import java.time.LocalDate;
import java.util.Objects;

public class ITTicket {

    private final String ticketId;
    private final ITUser reporter;
    private Priority priority;
    private final String title;
    private final LocalDate createdDate;
    private LocalDate resolvedDate;
    private TicketState state;

    public ITTicket(String ticketId, ITUser reporter, Priority priority, String title, LocalDate createdDate, LocalDate resolvedDate, TicketState state) {
        this.ticketId = ITSupportUtils.cleanNotBlank(ticketId, "Ticket ID");
        this.reporter = ITSupportUtils.checkNotNull(reporter, "Reporter");
        this.priority = ITSupportUtils.checkNotNull(priority, "Priority");
        this.title = ITSupportUtils.cleanNotBlank(title, "Title");
        this.createdDate = ITSupportUtils.checkCreatedDate(createdDate);
        setResolvedDate(resolvedDate);
        setState(state != null ? state : TicketState.OPEN);
    }

    public void setPriority(Priority priority) {
        this.priority = ITSupportUtils.checkNotNull(priority, "Priority");
    }

    public void setResolvedDate(LocalDate resolvedDate) {
        this.resolvedDate = ITSupportUtils.checkResolvedDate(resolvedDate, this.createdDate);
    }

    public void setState(TicketState state) {
        this.state = ITSupportUtils.checkNotNull(state, "Ticket state");
    }

    public String getTicketId() {
        return ticketId;
    }

    public ITUser getReporter() {
        return reporter;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDate getResolvedDate() {
        return resolvedDate;
    }

    public TicketState getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ITTicket ITTicket1 = (ITTicket) o;
        return Objects.equals(ticketId, ITTicket1.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId);
    }

    @Override
    public String toString() {
        return "ITTicket{" +
                "ticketId='" + ticketId + '\'' +
                ", reporter=" + reporter +
                ", priority=" + priority +
                ", title='" + title + '\'' +
                ", createdDate=" + createdDate +
                ", resolvedDate=" + resolvedDate +
                ", state=" + state +
                '}';
    }
}
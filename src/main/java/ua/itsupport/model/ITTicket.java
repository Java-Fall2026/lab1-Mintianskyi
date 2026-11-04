package ua.itsupport.model;

import ua.common.BaseEntity;
import ua.itsupport.util.ITSupportUtils;

import java.time.LocalDate;
import java.util.Objects;

public class ITTicket extends BaseEntity {

    private final String ticketId;
    private final ITUser reporter;
    private final String title;
    private final LocalDate createdDate;
    private String priority;
    private LocalDate resolvedDate;
    private String state;

    public ITTicket(String ticketId, ITUser reporter, String priority,
                    String title, LocalDate createdDate, LocalDate resolvedDate, String state) {
        super();
        this.ticketId = ITSupportUtils.cleanNotBlank(ticketId, "Ticket ID");
        this.reporter = ITSupportUtils.checkNotNull(reporter, "Reporter");
        this.title = ITSupportUtils.cleanNotBlank(title, "Title");
        this.createdDate = ITSupportUtils.checkCreatedDate(createdDate);

        setPriority(priority);
        setResolvedDate(resolvedDate);
        setState(state);
    }

    public final void setPriority(String priority) {
        this.priority = ITSupportUtils.cleanPriority(priority);
    }

    public final void setResolvedDate(LocalDate resolvedDate) {
        this.resolvedDate = ITSupportUtils.checkResolvedDate(resolvedDate, this.createdDate);
    }

    public final void setState(String state) {
        this.state = ITSupportUtils.cleanState(state);
    }

    public String getTicketId() {
        return ticketId;
    }

    public ITUser getReporter() {
        return reporter;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public String getPriority() {
        return priority;
    }

    public LocalDate getResolvedDate() {
        return resolvedDate;
    }

    public String getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ITTicket itTicket = (ITTicket) o;
        return Objects.equals(ticketId, itTicket.ticketId);
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
                ", title='" + title + '\'' +
                ", createdDate=" + createdDate +
                ", priority='" + priority + '\'' +
                ", resolvedDate=" + resolvedDate +
                ", state='" + state + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
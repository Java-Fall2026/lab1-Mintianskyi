package ua.itsupport.model;

import ua.common.BaseEntity;
import ua.itsupport.util.ITSupportUtils;
import java.time.LocalDate;
import java.util.Objects;

public class ITTicket extends BaseEntity {
    private String ticketId;
    private ITUser reporter;
    private String priority;
    private String title;
    private LocalDate createdDate;
    private LocalDate resolvedDate;
    private String state;

    public ITTicket(String ticketId, ITUser reporter, String priority, String title, LocalDate createdDate, LocalDate resolvedDate, String state) {
        super();
        setTicketId(ticketId);
        setReporter(reporter);
        setPriority(priority);
        setTitle(title);
        setCreatedDate(createdDate);
        setResolvedDate(resolvedDate);
        setState(state);
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ITSupportUtils.validateAndNormalizeString(ticketId, "Ticket ID");
    }

    public ITUser getReporter() {
        return reporter;
    }

    public void setReporter(ITUser reporter) {
        if (reporter == null) throw new IllegalArgumentException("Reporter must not be null");
        this.reporter = reporter;
    }

    public String getPriority() {
        return priority;
    }

    public final void setPriority(String priority) {
        this.priority = ITSupportUtils.validateAndNormalizePriority(priority);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = ITSupportUtils.validateAndNormalizeString(title, "Title");
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        if (createdDate == null) throw new IllegalArgumentException("Created date must not be null");
        if (createdDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Created date cannot be in the future, got: " + createdDate);
        }
        this.createdDate = createdDate;
    }

    public LocalDate getResolvedDate() {
        return resolvedDate;
    }

    public final void setResolvedDate(LocalDate resolvedDate) {
        if (resolvedDate != null && createdDate != null && resolvedDate.isBefore(createdDate)) {
            throw new IllegalArgumentException("Resolved date must be >= createdDate, got: " + resolvedDate);
        }
        this.resolvedDate = resolvedDate;
    }

    public String getState() {
        return state;
    }

    public final void setState(String state) {
        this.state = ITSupportUtils.validateAndNormalizeState(state);
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
        return "ITTicket{createdAt=" + createdAt + ", ticketId='" + ticketId + "', priority='" + priority + "', title='" + title + "', state='" + state + "'}";
    }
}
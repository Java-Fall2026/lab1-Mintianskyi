package ua.itsupport.model;

import ua.itsupport.util.ITSupportUtils;
import java.time.LocalDate;

public record ITTicket(
        String ticketId,
        ITUser reporter,
        Priority priority,
        String title,
        LocalDate createdDate,
        LocalDate resolvedDate,
        String state
) {
    public ITTicket {
        ticketId = ITSupportUtils.cleanNotBlank(ticketId, "Ticket ID");
        ITSupportUtils.checkNotNull(reporter, "Reporter");
        ITSupportUtils.checkNotNull(priority, "Priority");
        title = ITSupportUtils.cleanNotBlank(title, "Title");
        ITSupportUtils.checkCreatedDate(createdDate);
        ITSupportUtils.checkResolvedDate(resolvedDate, createdDate);
        state = ITSupportUtils.cleanState(state);
    }
}
package ua.itsupport.model;

import ua.itsupport.util.ITSupportUtils;

public record ITUser(String email, String fullName, String department) {
    public ITUser {
        email = ITSupportUtils.cleanAndLower(email, "Email");
        fullName = ITSupportUtils.cleanNotBlank(fullName, "Full name");
        department = ITSupportUtils.cleanNotBlank(department, "Department");
    }
}
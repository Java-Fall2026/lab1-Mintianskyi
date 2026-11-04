package ua.itsupport.model;

import ua.common.BaseEntity;
import ua.itsupport.util.ITSupportUtils;

import java.util.Objects;

public class ITUser extends BaseEntity {

    private final String email;
    private final String fullName;
    private final String department;

    private ITUser(String email, String fullName, String department) {
        super();
        this.email = ITSupportUtils.cleanAndLower(email, "Email");
        this.fullName = ITSupportUtils.cleanNotBlank(fullName, "Full name");
        this.department = ITSupportUtils.cleanNotBlank(department, "Department");
    }

    public static ITUser of(String email, String fullName, String department) {
        return new ITUser(email, fullName, department);
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ITUser itUser = (ITUser) o;
        return Objects.equals(email, itUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "ITUser{" +
                "email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", department='" + department + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
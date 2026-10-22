package ua.itsupport.model;

import ua.common.BaseEntity;
import ua.itsupport.util.ITSupportUtils;
import java.util.Objects;

public class ITUser extends BaseEntity {
    private String email;
    private String fullName;
    private String department;

    public ITUser(String email, String fullName, String department) {
        super();
        setEmail(email);
        setFullName(fullName);
        setDepartment(department);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = ITSupportUtils.validateAndNormalizeEmail(email);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = ITSupportUtils.validateAndNormalizeString(fullName, "Full name");
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = ITSupportUtils.validateAndNormalizeString(department, "Department");
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
        return "ITUser{createdAt=" + createdAt + ", email='" + email + "', fullName='" + fullName + "', department='" + department + "'}";
    }
}
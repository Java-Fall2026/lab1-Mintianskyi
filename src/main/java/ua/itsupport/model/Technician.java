package ua.itsupport.model;

import ua.itsupport.util.ITSupportUtils;
import java.time.LocalDateTime;
import java.util.Objects;

public class Technician {

    private final String techCode;
    private final String name;
    private int skillLevel;
    private final LocalDateTime createdAt;

    public Technician(String techCode, String name, int skillLevel) {
        this.techCode = ITSupportUtils.cleanNotBlank(techCode, "Tech code");
        this.name = ITSupportUtils.cleanNotBlank(name, "Technician name");
        setSkillLevel(skillLevel);
        this.createdAt = LocalDateTime.now();
    }

    public final void setSkillLevel(int skillLevel) {
        this.skillLevel = ITSupportUtils.checkSkillLevel(skillLevel);
    }

    public String getTechCode() {
        return techCode;
    }

    public String getName() {
        return name;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Technician that = (Technician) o;
        return Objects.equals(techCode, that.techCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(techCode);
    }

    @Override
    public String toString() {
        return "Technician{" +
                "techCode='" + techCode + '\'' +
                ", name='" + name + '\'' +
                ", skillLevel=" + skillLevel +
                ", createdAt=" + createdAt +
                '}';
    }
}
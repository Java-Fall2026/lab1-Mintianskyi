package ua.itsupport.model;

import ua.common.BaseEntity;
import ua.itsupport.util.ITSupportUtils;

import java.util.Objects;

public class Technician extends BaseEntity {

    private final String techCode;
    private final String name;
    private int skillLevel;

    public Technician(String techCode, String name, int skillLevel) {
        super();
        this.techCode = ITSupportUtils.cleanNotBlank(techCode, "Tech code");
        this.name = ITSupportUtils.cleanNotBlank(name, "Technician name");
        setSkillLevel(skillLevel);
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
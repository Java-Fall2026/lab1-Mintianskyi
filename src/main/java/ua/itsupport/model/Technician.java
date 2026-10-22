package ua.itsupport.model;

import ua.common.BaseEntity;
import ua.itsupport.util.ITSupportUtils;
import java.util.Objects;

public class Technician extends BaseEntity {
    private String techCode;
    private String name;
    private int skillLevel;

    public Technician(String techCode, String name, int skillLevel) {
        super();
        setTechCode(techCode);
        setName(name);
        setSkillLevel(skillLevel);
    }

    public String getTechCode() {
        return techCode;
    }

    public void setTechCode(String techCode) {
        this.techCode = ITSupportUtils.validateAndNormalizeString(techCode, "Tech code");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = ITSupportUtils.validateAndNormalizeString(name, "Name");
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public final void setSkillLevel(int skillLevel) {
        this.skillLevel = ITSupportUtils.validateSkillLevel(skillLevel);
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
        return "Technician{createdAt=" + createdAt + ", techCode='" + techCode + "', name='" + name + "', skillLevel=" + skillLevel + "}";
    }
}
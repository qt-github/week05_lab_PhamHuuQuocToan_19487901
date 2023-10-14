package vn.edu.iuh.fit.backend.enums;

import lombok.Getter;

@Getter
public enum SkillType {
    SOFT_SKILL(1),
    UNSPECIFIED(2),
    TECHNICAL_SKILL(3);

    public final int value;

    SkillType(int value) {
        this.value = value;
    }

    public static SkillType fromValue(int value) {
        for (SkillType skillType : SkillType.values()) {
            if (skillType.getValue() == value) {
                return skillType;
            }
        }
        return null;
    }

    public static SkillType fromName(String name) {
        for (SkillType skillType : SkillType.values()) {
            if (skillType.name().equals(name)) {
                return skillType;
            }
        }
        return null;
    }
}

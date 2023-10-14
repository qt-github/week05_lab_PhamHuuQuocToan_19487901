package vn.edu.iuh.fit.backend.enums;

import lombok.Getter;

@Getter
public enum SkillLevel {
    MASTER(1),
    BEGINNER(2),
    ADVANCED(3),
    PROFESSIONAL(4),
    INTERMEDIATE(5);



    private final int value;

    SkillLevel(int value) {
        this.value = value;
    }


    public static SkillLevel fromValue(int value) {
        for (SkillLevel skillLevel : SkillLevel.values()) {
            if (skillLevel.getValue() == value) {
                return skillLevel;
            }
        }
        return null;
    }

    public static SkillLevel fromName(String name) {
        for (SkillLevel skillLevel : SkillLevel.values()) {
            if (skillLevel.name().equals(name)) {
                return skillLevel;
            }
        }
        return null;
    }

}
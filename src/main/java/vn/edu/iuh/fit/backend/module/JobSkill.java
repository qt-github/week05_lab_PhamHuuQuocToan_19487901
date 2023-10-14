package vn.edu.iuh.fit.backend.module;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import vn.edu.iuh.fit.backend.enums.SkillLevel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class JobSkill {
    @Column(name = "more_infos", length = 1000)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String moreInfos;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @Enumerated
    @Column(name = "skill_level")
    private SkillLevel skillLevel;

}
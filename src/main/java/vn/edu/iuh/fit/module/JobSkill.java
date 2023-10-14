package vn.edu.iuh.fit.module;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

    @Column(name = "skill_level", length = 4)
    @JdbcTypeCode(SqlTypes.TINYINT)
    private int skillLevel;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

}
package vn.edu.iuh.fit.backend.module;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import vn.edu.iuh.fit.backend.enums.SkillLevel;

@Getter
@Setter
@Embeddable
public class CandidateSkill {

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @Enumerated
    @Column(name = "skill_level")
    private SkillLevel skillLevel;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Column(name = "more_infos", length = 1000)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String moreInfos;

}
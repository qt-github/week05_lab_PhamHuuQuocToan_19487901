package vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.module;

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
public class CandidateSkill {
    @Column(name = "more_infos", length = 1000)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String moreInfos;
    @Column(name = "skill_level", length = 4)
    @JdbcTypeCode(SqlTypes.TINYINT)
    private int skillLevel;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

}
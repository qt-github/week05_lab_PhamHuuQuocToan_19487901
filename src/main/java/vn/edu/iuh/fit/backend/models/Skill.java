package vn.edu.iuh.fit.backend.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import vn.edu.iuh.fit.backend.enums.SkillType;

import java.util.List;
import java.util.Objects;

//Read skill list at: https://www.yourdictionary.com/articles/examples-skills-list
//API: https://github.com/workforce-data-initiative/skills-api/wiki/API-Overview#swagger-ui-test-client
@Entity
@Table(name = "skill")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private long id;
    @Column(name = "skill_name", nullable = false, length = 150)
    private String skillName;
    @Column(name = "skill_type", nullable = false)
    private SkillType type;
    @Column(name = "skill_desc", nullable = false, length = 300)
    private String skillDescription;

    @OneToMany(mappedBy = "skill")
    @ToString.Exclude
    private List<JobSkill>jobSkills;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Skill skill = (Skill) o;
        return Objects.equals(getId(), skill.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
package vn.edu.iuh.fit.backend.module;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.SqlTypes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exp_id", nullable = false)
    private Long id;

    @Column(name = "from_date")
    @JdbcTypeCode(SqlTypes.DATE)
    private Date fromDate;

    @Column(name = "to_date")
    @JdbcTypeCode(SqlTypes.DATE)
    private Date toDate;

    @Column(name = "role", length = 100)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String role;

    @Column(name = "company_name", length = 120)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String companyName;

    @Column(name = "work_desc", length = 400)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String workDesc;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ElementCollection
    @CollectionTable(name = "experience_candidateSkill", joinColumns = @JoinColumn(name = "owner_id"))
    private List<CandidateSkill> candidateSkill = new ArrayList<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Experience that = (Experience) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
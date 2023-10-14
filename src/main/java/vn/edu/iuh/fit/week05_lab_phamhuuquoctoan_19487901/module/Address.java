package vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.module;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.SqlTypes;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 20)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name = "street", length = 150)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String street;

    @Column(name = "city", length = 50)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String city;

    @Column(name = "country", length = 6)
    @JdbcTypeCode(SqlTypes.SMALLINT)
    private int country;

    @Column(name = "number", length = 20)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String number;

    @Column(name = "zipcode", length = 7)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String zipcode;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "comp_id")
    private Company company;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Address address = (Address) o;
        return getId() != null && Objects.equals(getId(), address.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
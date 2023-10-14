package vn.edu.iuh.fit.backend.module;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.SqlTypes;

import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comp_id", nullable = false, length = 20)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name = "about", length = 2000)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String about;

    @Column(name = "email")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String email;

    @Column(name = "comp_name")
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String compName;

    @Column(name = "phone")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String phone;

    @Column(name = "web_url")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String webUrl;

    @OneToMany(mappedBy = "company")
    @ToString.Exclude
    private Set<Address> addresses;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Company company = (Company) o;
        return getId() != null && Objects.equals(getId(), company.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
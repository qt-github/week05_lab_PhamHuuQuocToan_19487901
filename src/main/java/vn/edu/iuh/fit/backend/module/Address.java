package vn.edu.iuh.fit.backend.module;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.SqlTypes;

import java.util.Objects;

@Getter
@Setter
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

    @Column(name = "country", columnDefinition = "smallint(6)")
    private CountryCode country;

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

    public Address(String street, String city, String number, String zipcode) {
        this.street = street;
        this.city = city;
        this.number = number;
        this.zipcode = zipcode;
    }
}
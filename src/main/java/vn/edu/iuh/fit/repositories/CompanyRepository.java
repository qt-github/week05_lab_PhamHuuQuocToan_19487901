package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.module.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
package vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.services;

import vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.module.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    void save(Company company);
    List<Company> findAll();
    Optional<Company> findById(Long id);
    boolean deleteById(Long id);
    boolean update(Company company);
}

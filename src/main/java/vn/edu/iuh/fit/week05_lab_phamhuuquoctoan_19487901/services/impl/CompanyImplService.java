package vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.services.impl;

import vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.module.Company;
import vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.repositories.CompanyRepository;
import vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.services.CompanyService;

import java.util.List;
import java.util.Optional;

public class CompanyImplService implements CompanyService {
    private final CompanyRepository repo;

    public CompanyImplService(CompanyRepository repo) {
        this.repo = repo;
    }
    @Override
    public void save(Company company) {
        repo.save(company);
    }

    @Override
    public List<Company> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Company> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Company> company = repo.findById(id);
        if (company.isPresent()) {
            repo.delete(company.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Company company) {
        Optional<Company> companyOptional = repo.findById(company.getId());
        if (companyOptional.isPresent()) {
            repo.save(company);
            return true;
        }
        return false;
    }
}

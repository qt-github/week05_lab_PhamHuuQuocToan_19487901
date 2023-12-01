package vn.edu.iuh.fit.backend.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Company;

import java.util.List;
import java.util.Optional;

@Service
public interface CompanyService {

    List<Company> getAll();

    Optional<Company> getById(Long id);

    void save(Company company);

    Company update(Company company);

    void delete(Long id);

    void sendSimpleMessage(String email);


}

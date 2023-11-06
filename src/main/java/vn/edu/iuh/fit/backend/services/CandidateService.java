package vn.edu.iuh.fit.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.iuh.fit.frontend.models.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateService {
    void save(Candidate candidate);
    List<Candidate> findAll();
    Optional<Candidate> findById(Long id);
    boolean deleteById(Long id);
    boolean update(Candidate candidate);
    Page<Candidate> findAllPaginated(int page, int size, String sortField, String sortDir);
    Page<Candidate> findPaginated(Pageable pageable);
}

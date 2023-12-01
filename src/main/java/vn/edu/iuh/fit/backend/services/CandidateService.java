package vn.edu.iuh.fit.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Candidate;

import java.util.Optional;

@Service
public interface CandidateService {
    Page<Candidate> findAllPaginated(int page, int size, String sortField, String sortDir);
    Page<Candidate> findPaginated(Pageable pageable);
    Candidate updateCandidate(Long id, Candidate updatedCandidate);
    Optional<Candidate> getCandidateById(Long id);
}

package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.frontend.models.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
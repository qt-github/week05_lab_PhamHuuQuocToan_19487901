package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.module.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
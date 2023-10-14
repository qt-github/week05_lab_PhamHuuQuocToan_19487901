package vn.edu.iuh.fit.backend.services;

import vn.edu.iuh.fit.backend.module.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateService {
    void save(Candidate candidate);
    List<Candidate> findAll();
    Optional<Candidate> findById(Long id);
    boolean deleteById(Long id);
    boolean update(Candidate candidate);
}

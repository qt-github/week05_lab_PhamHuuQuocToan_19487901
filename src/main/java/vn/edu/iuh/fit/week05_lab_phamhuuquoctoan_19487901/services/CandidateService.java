package vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.services;

import vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.module.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateService {
    void save(Candidate candidate);
    List<Candidate> findAll();
    Optional<Candidate> findById(Long id);
    boolean deleteById(Long id);
    boolean update(Candidate candidate);
}

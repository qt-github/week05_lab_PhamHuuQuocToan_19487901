package vn.edu.iuh.fit.services.impl;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.module.Candidate;
import vn.edu.iuh.fit.repositories.CandidateRepository;
import vn.edu.iuh.fit.services.CandidateService;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateImplService implements CandidateService {
    private final CandidateRepository repo;

    public CandidateImplService(CandidateRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(Candidate candidate) {
        repo.save(candidate);
    }

    @Override
    public List<Candidate> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Candidate> findById(Long id) {
        return repo.findById(id);
    }


    @Override
    public boolean deleteById(Long id) {
        Optional<Candidate> candidate = repo.findById(id);
        if (candidate.isPresent()) {
            repo.delete(candidate.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Candidate candidate) {
        Optional<Candidate> candidate1 = repo.findById(candidate.getId());
        if (candidate1.isPresent()) {
            repo.save(candidate);
            return true;
        }
        return false;
    }
}

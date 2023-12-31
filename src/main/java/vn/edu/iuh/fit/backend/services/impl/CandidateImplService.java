package vn.edu.iuh.fit.backend.services.impl;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.services.CandidateService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class CandidateImplService implements CandidateService {
    private final CandidateRepository repo;

    public CandidateImplService(CandidateRepository repo) {
        this.repo = repo;
    }

    @Override
    public Page<Candidate> findAllPaginated(int page, int size, String sortField, String sortDir) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return repo.findAll(pageable);
    }

    @Override
    public Page<Candidate> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Candidate> list;
        List<Candidate> candidates = repo.findAll();

        if (candidates.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, candidates.size());
            list = candidates.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), candidates.size());
    }

    public Candidate getCandidateById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Candidate updateCandidate(Long id, Candidate updatedCandidate) {
        return repo.findById(id).map(existingCandidate -> {
            existingCandidate.setFullName(updatedCandidate.getFullName());
            existingCandidate.setCandidateSkills(updatedCandidate.getCandidateSkills());
            existingCandidate.setAddress(updatedCandidate.getAddress());
            existingCandidate.setDob(updatedCandidate.getDob());
            existingCandidate.setEmail(updatedCandidate.getEmail());
            existingCandidate.setExperiences(updatedCandidate.getExperiences());

            return repo.save(existingCandidate);
        }).orElse(null);
    }
}

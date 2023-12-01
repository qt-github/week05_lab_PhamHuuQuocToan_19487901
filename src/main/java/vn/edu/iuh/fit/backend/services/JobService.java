package vn.edu.iuh.fit.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Job;

import java.util.List;
import java.util.Optional;

@Service
public interface JobService {
    Page<Job> findAllPaginated(int page, int size, String sortField, String sortDir);

    Page<Job> findPaginated(Pageable pageable);

    List<Job> recommendJobsForCandidate(Long candidateId);

    List<Candidate> recommendCandidatesForJob(Long jobId);

    void save(Job job);

    void deleteById(Long id);

    Job findById(Long id);


}

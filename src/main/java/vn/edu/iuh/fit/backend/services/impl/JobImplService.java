package vn.edu.iuh.fit.backend.services.impl;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.models.JobSkill;
import vn.edu.iuh.fit.backend.models.Skill;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.repositories.JobRepository;
import vn.edu.iuh.fit.backend.services.JobService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JobImplService implements JobService {
    private final JobRepository repo;
    private final CandidateRepository candidateRepository;

    public JobImplService(JobRepository repo, CandidateRepository candidateRepository) {
        this.repo = repo;
        this.candidateRepository = candidateRepository;
    }

    @Override
    public Page<Job> findAllPaginated(int page, int size, String sortField, String sortDir) {
        return repo.findAll(PageRequest.of(page - 1, size, Sort.by(Sort.Direction.fromString(sortDir), sortField)));
    }

    @Override
    public Page<Job> findPaginated(Pageable pageable) {
        List<Job> jobs = repo.findAll();
        int startItem = pageable.getPageNumber() * pageable.getPageSize();
        List<Job> list = jobs.subList(startItem, Math.min(startItem + pageable.getPageSize(), jobs.size()));
        return new PageImpl<>(list, pageable, jobs.size());
    }

    @Override
    public List<Job> recommendJobsForCandidate(Long candidateId) {
        return candidateRepository.findById(candidateId)
                .map(candidate -> repo.findByJobSkillsSkillIdIn(candidate.getCandidateSkills().stream()
                        .map(candidateSkill -> candidateSkill.getSkill().getId())
                        .collect(Collectors.toList())))
                .orElse(Collections.emptyList());
    }

    @Override
    public List<Candidate> recommendCandidatesForJob(Long jobId) {
        return repo.findById(jobId)
                .map(job -> candidateRepository.findByCandidateSkillsSkillIn(job.getJobSkills().stream()
                        .map(JobSkill::getSkill)
                        .collect(Collectors.toList())))
                .orElse(Collections.emptyList());
    }

    public void save(Job job) {
        repo.save(job);
    }

    public Job findById(Long jobId) {
        return repo.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}

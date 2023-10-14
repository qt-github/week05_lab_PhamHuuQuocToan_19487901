package vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.services.impl;

import vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.module.Job;
import vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.repositories.JobRepository;
import vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.services.JobService;

import java.util.List;
import java.util.Optional;

public class JobImplService implements JobService {
    private final JobRepository repo;

    public JobImplService(JobRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(Job job) {
        repo.save(job);
    }

    @Override
    public List<Job> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Job> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Job> job = repo.findById(id);
        if (job.isPresent()) {
            repo.delete(job.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Job job) {
        Optional<Job> jobOptional = repo.findById(job.getId());
        if (jobOptional.isPresent()) {
            repo.save(job);
            return true;
        }
        return false;
    }
}
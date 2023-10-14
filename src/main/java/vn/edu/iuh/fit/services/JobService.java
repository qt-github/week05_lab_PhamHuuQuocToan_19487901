package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.module.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {
    void save(Job job);
    List<Job> findAll();
    Optional<Job> findById(Long id);
    boolean deleteById(Long id);
    boolean update(Job job);
}

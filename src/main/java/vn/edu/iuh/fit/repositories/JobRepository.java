package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.module.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
}
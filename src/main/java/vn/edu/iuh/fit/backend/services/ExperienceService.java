package vn.edu.iuh.fit.backend.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Experience;

@Service
public interface ExperienceService {
    void getAll();
    void save(Experience experience);
}

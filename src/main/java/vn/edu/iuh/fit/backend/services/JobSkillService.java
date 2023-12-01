package vn.edu.iuh.fit.backend.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.JobSkill;

@Service
public interface JobSkillService {

    void save(JobSkill jobSkill);
}

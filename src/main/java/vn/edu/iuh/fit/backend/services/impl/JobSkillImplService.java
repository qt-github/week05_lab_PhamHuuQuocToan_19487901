package vn.edu.iuh.fit.backend.services.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.JobSkill;
import vn.edu.iuh.fit.backend.repositories.JobSkillRepository;
import vn.edu.iuh.fit.backend.services.JobSkillService;

@Component
public class JobSkillImplService implements JobSkillService {
    private final JobSkillRepository jobSkillRepository;

    public JobSkillImplService(JobSkillRepository jobSkillRepository) {
        this.jobSkillRepository = jobSkillRepository;
    }

    @Override
    public void save(JobSkill jobSkill) {
        jobSkillRepository.save(jobSkill);
    }
}

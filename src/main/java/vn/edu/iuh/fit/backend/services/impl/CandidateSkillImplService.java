package vn.edu.iuh.fit.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.backend.models.CandidateSkill;
import vn.edu.iuh.fit.backend.repositories.CandidateSkillRepository;
import vn.edu.iuh.fit.backend.services.CandidateSkillService;

@Component
public class CandidateSkillImplService implements CandidateSkillService {

    private final CandidateSkillRepository candidateSkillRepository;

    public CandidateSkillImplService(CandidateSkillRepository candidateSkillRepository) {
        this.candidateSkillRepository = candidateSkillRepository;
    }

    public void save(CandidateSkill candidateSkill){candidateSkillRepository.save(candidateSkill);}
}

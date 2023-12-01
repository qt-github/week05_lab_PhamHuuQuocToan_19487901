package vn.edu.iuh.fit.backend.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.CandidateSkill;

@Service
public interface CandidateSkillService {
    void save(CandidateSkill candidateSkill);
}

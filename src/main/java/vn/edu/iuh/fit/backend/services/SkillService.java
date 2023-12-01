package vn.edu.iuh.fit.backend.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Skill;

import java.util.List;
import java.util.Optional;

@Service
public interface SkillService {
Skill addSkill(Skill skill);
List<Skill> getAllSkills();
List<Skill> suggestSkillsForCandidate(Long candidateId);
Optional<Skill> getSkillById(Long id);
}

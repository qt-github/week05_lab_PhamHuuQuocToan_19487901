package vn.edu.iuh.fit.backend.services.impl;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.repositories.SkillRepository;
import vn.edu.iuh.fit.frontend.models.Skill;
import vn.edu.iuh.fit.backend.services.SkillService;

import java.util.List;
import java.util.Optional;

@Service
public class SkillImplService implements SkillService {
    private final SkillRepository repo;

    public SkillImplService(SkillRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(Skill skill) {
        repo.save(skill);
    }

    @Override
    public List<Skill> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Skill> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Skill> skill = repo.findById(id);
        if (skill.isPresent()) {
            repo.delete(skill.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Skill skill) {
        Optional<Skill> skillOptional = repo.findById(skill.getId());
        if (skillOptional.isPresent()) {
            repo.save(skill);
            return true;
        }
        return false;
    }
}

package vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.services.impl;

import vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.module.Skill;
import vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.repositories.SkillRepository;
import vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.services.SkillService;

import java.util.List;
import java.util.Optional;

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

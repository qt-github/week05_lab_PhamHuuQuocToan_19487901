package vn.edu.iuh.fit.backend.services;

import vn.edu.iuh.fit.frontend.models.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    void save(Skill skill);
    List<Skill> findAll();
    Optional<Skill> findById(Long id);
    boolean deleteById(Long id);
    boolean update(Skill skill);
}

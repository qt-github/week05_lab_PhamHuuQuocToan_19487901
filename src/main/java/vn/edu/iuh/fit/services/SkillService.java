package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.module.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    void save(Skill skill);
    List<Skill> findAll();
    Optional<Skill> findById(Long id);
    boolean deleteById(Long id);
    boolean update(Skill skill);
}

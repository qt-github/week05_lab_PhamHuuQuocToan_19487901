package vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.services;

import vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901.module.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    void save(Skill skill);
    List<Skill> findAll();
    Optional<Skill> findById(Long id);
    boolean deleteById(Long id);
    boolean update(Skill skill);
}

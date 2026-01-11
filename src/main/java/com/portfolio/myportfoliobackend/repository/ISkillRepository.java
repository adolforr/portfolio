package com.portfolio.myportfoliobackend.repository;

import com.portfolio.myportfoliobackend.model.PersonalInfo;
import com.portfolio.myportfoliobackend.model.Skill;

import java.util.List;
import java.util.Optional;

public interface ISkillRepository {
    Skill save(Skill skill);
    Optional<Skill> findById(Long id);
    List<Skill> findAll();
    void deleteById(Long id);
    List<Skill> findAllByPersonalInfoId(Long personalInfoId);
}

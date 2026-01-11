package com.portfolio.myportfoliobackend.repository;

import com.portfolio.myportfoliobackend.model.Education;
import com.portfolio.myportfoliobackend.model.Skill;

import java.util.List;
import java.util.Optional;

public interface IEducationRepository {
    Education save(Education education);
    Optional<Education> findById(Long id);
    List<Education> findAll();
    void deleteById(Long id);
    List<Education> findAllByPersonalInfoId(Long personalInfoId);
}

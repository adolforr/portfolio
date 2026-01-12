package com.portfolio.myportfoliobackend.service;

import com.portfolio.myportfoliobackend.model.Experience;

import java.util.List;
import java.util.Optional;

public interface IExperienceService {
    List<Experience> findAll();
    Optional<Experience> findById(Long id);
    Experience save(Experience experience);
    void deleteById(Long id);
    List<Experience> findExperienceByPersonalInfoId(Long personalInfoId);
}

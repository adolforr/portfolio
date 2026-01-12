package com.portfolio.myportfoliobackend.repository;


import com.portfolio.myportfoliobackend.model.Experience;

import java.util.List;
import java.util.Optional;

public interface IExperienceRepository {
    List<Experience> findAll();
    Optional<Experience> findById(Long id);
    Experience save(Experience experience);
    void deleteById(Long id);
    List<Experience> findByPersonalInfoId(Long personalInfoId);
}

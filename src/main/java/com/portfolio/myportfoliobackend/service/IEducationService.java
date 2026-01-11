package com.portfolio.myportfoliobackend.service;

import com.portfolio.myportfoliobackend.model.Education;

import java.util.List;
import java.util.Optional;

public interface IEducationService {
    Education save(Education education);
    Optional<Education> findById(Long id);
    List<Education> findAll();
    void deleteById(Long id);
    List<Education> findAllByPersonalInfoId(Long personalInfoId);
}

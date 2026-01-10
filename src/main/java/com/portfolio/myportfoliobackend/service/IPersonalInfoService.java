package com.portfolio.myportfoliobackend.service;

import com.portfolio.myportfoliobackend.model.PersonalInfo;

import java.util.List;
import java.util.Optional;

public interface IPersonalInfoService {
    PersonalInfo save(PersonalInfo personalInfo);
    Optional<PersonalInfo> findById(Long id);
    List<PersonalInfo> findAll();
    void deleteById(Long id);
}

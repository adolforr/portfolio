package com.portfolio.myportfoliobackend.service;

import com.portfolio.myportfoliobackend.model.Education;
import com.portfolio.myportfoliobackend.repository.IEducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducationServiceImp implements IEducationService{

    private final IEducationRepository educationRepository;

    @Override
    public Education save(Education education) {
        if(education.getStartDate() == null) {
            throw new IllegalArgumentException("Start date cannot be null.");
        }

        if(education.getEndDate() != null && education.getStartDate().isAfter(education.getEndDate())) {
            throw new IllegalArgumentException("Start date cannot be after end date.");

        }
        return educationRepository.save(education);
    }

    @Override
    public Optional<Education> findById(Long id) {
        return educationRepository.findById(id);
    }

    @Override
    public List<Education> findAll() {
        return educationRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        educationRepository.deleteById(id);
    }

    @Override
    public List<Education> findAllByPersonalInfoId(Long personalInfoId) {
        return educationRepository.findAllByPersonalInfoId(personalInfoId);
    }
}

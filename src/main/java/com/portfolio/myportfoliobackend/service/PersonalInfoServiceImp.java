package com.portfolio.myportfoliobackend.service;

import com.portfolio.myportfoliobackend.model.PersonalInfo;
import com.portfolio.myportfoliobackend.repository.IPersonalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalInfoServiceImp implements IPersonalInfoService{

    private final IPersonalInfoRepository personalInfoRepository;

    @Override
    public PersonalInfo save(PersonalInfo personalInfo) {
        return personalInfoRepository.save(personalInfo);
    }

    @Override
    public Optional<PersonalInfo> findById(Long id) {
        return personalInfoRepository.findById(id);
    }

    @Override
    public List<PersonalInfo> findAll() {
        return personalInfoRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        personalInfoRepository.deleteById(id);
    }
}

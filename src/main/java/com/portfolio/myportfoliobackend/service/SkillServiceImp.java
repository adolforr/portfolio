package com.portfolio.myportfoliobackend.service;

import com.portfolio.myportfoliobackend.model.Skill;
import com.portfolio.myportfoliobackend.repository.ISkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillServiceImp implements ISkillService{

    private final ISkillRepository skillRepository;

    @Override
    public Skill save(Skill skill) {


        if(skill.getSkillLevelPercentage() < 0 || skill.getSkillLevelPercentage() > 100) {
            throw new IllegalArgumentException("Skill level percentage must be between 0 and 100.");
        }

        return skillRepository.save(skill);
    }

    @Override
    public Optional<Skill> findById(Long id) {
        return skillRepository.findById(id);
    }

    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    public List<Skill> findAllByPersonalInfoId(Long personalInfoId) {
        return skillRepository.findAllByPersonalInfoId(personalInfoId);
    }
}

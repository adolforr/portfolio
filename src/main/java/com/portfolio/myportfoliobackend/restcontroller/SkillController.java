package com.portfolio.myportfoliobackend.restcontroller;

import com.portfolio.myportfoliobackend.model.Skill;
import com.portfolio.myportfoliobackend.service.ISkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {
    private final ISkillService skillService;


    @GetMapping
    public ResponseEntity<List<Skill>> findAllSkills() {
        return ResponseEntity.ok(skillService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> findSkillById(@PathVariable Integer id) {
        Optional<Skill> skill = skillService.findById(Long.valueOf(id));
        return skill.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/personal-info/{id}")
    public ResponseEntity<List<Skill>> findSkillsByPersonalInfoId(@PathVariable("id") Long personalInfoId) {
        List<Skill> skills = skillService.findAllByPersonalInfoId(personalInfoId);
        return ResponseEntity.ok(skills);
    }

    @PostMapping
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
        Skill newSkill = skillService.save(skill);
        return ResponseEntity.ok(newSkill);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable Integer id, @RequestBody Skill skill) {
        Skill existingSkill = skillService.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Skill not found for id: " + id));
        skill.setId(existingSkill.getId());
        Skill updatedSkill = skillService.save(skill);
        return ResponseEntity.ok(updatedSkill);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable Integer id) {
        skillService.deleteById(Long.valueOf(id));
    }
}

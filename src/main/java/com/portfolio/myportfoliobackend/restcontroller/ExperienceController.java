package com.portfolio.myportfoliobackend.restcontroller;

import com.portfolio.myportfoliobackend.model.Experience;
import com.portfolio.myportfoliobackend.service.IExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/experience")
@RequiredArgsConstructor
public class ExperienceController {
    private final IExperienceService experienceService;

    @GetMapping
    public ResponseEntity<List<Experience>> findAllExperiences(){
         return ResponseEntity.ok(experienceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experience> findExperienceById(@PathVariable Integer id){
        Optional<Experience> experience = experienceService.findById(Long.valueOf(id));
        return experience.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Experience> createExperience(@RequestBody Experience experience){
        Experience newExperience = experienceService.save(experience);
        return ResponseEntity.ok(newExperience);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Experience> updateExperience(@PathVariable Integer id, @RequestBody Experience experience) {
        Experience existingExperience = experienceService.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Experience not found for id: " + id));
        experience.setId(existingExperience.getId());
        Experience updatedExperience = experienceService.save(experience);
        return ResponseEntity.ok(updatedExperience);
    }

    @DeleteMapping("/{id}")
    public void deleteExperience(@PathVariable Integer id){
        experienceService.deleteById(Long.valueOf(id));
    }
}

package com.portfolio.myportfoliobackend.restcontroller;

import com.portfolio.myportfoliobackend.model.Education;
import com.portfolio.myportfoliobackend.service.IEducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/education")
@RequiredArgsConstructor
public class EducationController {

    public final IEducationService educationService;

    @GetMapping
    public ResponseEntity<List<Education>> findAllEducation(){
       return ResponseEntity.ok(educationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Education>> findEducationById(@PathVariable Long id){
        return ResponseEntity.ok(educationService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<Education> save(@RequestBody Education education) {
        Education newEducation = educationService.save(education);
        return ResponseEntity.ok(newEducation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Education> updateEducation(@PathVariable Long id, @RequestBody Education education) {
        Optional<Education> existingEducation = educationService.findById(id);
        if (existingEducation.isPresent()) {
            education.setId(id);
            Education updatedEducation = educationService.save(education);
            return ResponseEntity.ok(updatedEducation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        educationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

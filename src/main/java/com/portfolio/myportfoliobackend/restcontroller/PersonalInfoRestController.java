package com.portfolio.myportfoliobackend.restcontroller;

import com.portfolio.myportfoliobackend.model.PersonalInfo;
import com.portfolio.myportfoliobackend.service.IPersonalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personal-info")
@RequiredArgsConstructor
public class PersonalInfoRestController {

    private final IPersonalInfoService personalInfoService;

    @GetMapping("/all")
    public List<PersonalInfo> getAllPersonalInfo(){
        return personalInfoService.findAll();
    }


    @GetMapping("/{id}")
    public PersonalInfo getPersonalInfoById(@PathVariable Integer id){
        Optional <PersonalInfo> personalInfo = personalInfoService.findById(Long.valueOf(id));
        if(personalInfo.isPresent()){
            return personalInfo.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "personal info not found for id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<PersonalInfo> createPersonalInfo(@RequestBody PersonalInfo personalInfo){
        PersonalInfo newPersonalInfo = personalInfoService.save(personalInfo);
        return new ResponseEntity<>(newPersonalInfo, HttpStatus.CREATED);
    }


}

package com.portfolio.myportfoliobackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {
    private Long id;
    private  String firstName;
    private String lastName;
    private String title;
    private String profileDescription;
    private String ProfileImageUrl;
    private Integer yearsOfExperience;
    private String email;
    private String phone;
    private String linkedInUrl;
    private String githubUrl;

}

package com.portfolio.myportfoliobackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private Long id;
    private String projectName;
    private String projectDescription;
    private String projectImageUrl;
    private String projectUrl;
    private String personalInfoId;
}

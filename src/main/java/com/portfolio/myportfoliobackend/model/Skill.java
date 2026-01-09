package com.portfolio.myportfoliobackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    private Long id;
    private String skillName;
    private Integer skillLevelPorcentage;
    private String iconClass;
    private String personalInfoId;
}

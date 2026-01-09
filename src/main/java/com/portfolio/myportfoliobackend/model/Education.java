package com.portfolio.myportfoliobackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    private Long id;
    private String degree;
    private String institution;
    private String startDate;
    private String endDate;
    private String description;
    private String personalInfoId;
}

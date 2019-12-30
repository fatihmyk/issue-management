package com.fatihmayuk.issuemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {

    private Long id;
    private String projectName;
    private String projectCode;

}

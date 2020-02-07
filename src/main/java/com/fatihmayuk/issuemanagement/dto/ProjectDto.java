package com.fatihmayuk.issuemanagement.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Project Data Transfer Object")
public class ProjectDto {

    @ApiModelProperty(value = "Project ID")
    private Long id;

    @NotNull
    @ApiModelProperty(required = true,value = "Name Of Project")
    private String projectName;

    @ApiModelProperty(required = true,value = "Code Of Project")
    @NotNull
    private String projectCode;

    @ApiModelProperty(required = true,value = "Project Manager ID")
    @NotNull
    private Long managerId;

    @ApiModelProperty(required = true,value = "Project Manager Name")
    private UserDto manager;



}

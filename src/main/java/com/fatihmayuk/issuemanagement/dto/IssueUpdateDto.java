package com.fatihmayuk.issuemanagement.dto;

import com.fatihmayuk.issuemanagement.entity.IssueStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Issue Update Data Transfer Object")
public class IssueUpdateDto {

    @ApiModelProperty(required = true,value = "ID")
    private Long id;
    @ApiModelProperty(required = true,value = "Description")
    private  String description;
    @ApiModelProperty(required = true,value = "Issue Details")
    private String details;
    @ApiModelProperty(required = true,value = "Date")
    private Date date;
    @ApiModelProperty(required = true,value = "Issue Status")
    private IssueStatus issueStatus;
    @ApiModelProperty(required = true,value = "Assignee")
    private Long assignee_id;
    @ApiModelProperty(required = true,value = "Project")
    private Long project_id;
}

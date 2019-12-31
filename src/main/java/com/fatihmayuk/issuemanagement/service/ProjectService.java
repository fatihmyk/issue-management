package com.fatihmayuk.issuemanagement.service;

import com.fatihmayuk.issuemanagement.dto.ProjectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {

    ProjectDto save(ProjectDto projectDto);

    ProjectDto getById(Long id);

    Page<ProjectDto> getAllPageable(Pageable pageable);

    Boolean delete(ProjectDto projectDto);

    Boolean delete(Long id);

    ProjectDto getByProjectCode(String projectCode);

    List<ProjectDto> getByProjectCodeContains(String projectCode);

    ProjectDto update(Long id, ProjectDto projectDto);
}

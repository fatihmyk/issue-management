package com.fatihmayuk.issuemanagement.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatihmayuk.issuemanagement.dto.ProjectDto;
import com.fatihmayuk.issuemanagement.entity.Project;
import com.fatihmayuk.issuemanagement.repository.ProjectRepository;
import com.fatihmayuk.issuemanagement.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public Project save(Project project) {
        if (project.getProjectCode()==null){

            throw new IllegalArgumentException("Project code cannot be null!");

        }
        return projectRepository.save(project);
    }

    @Override
    public ProjectDto getById(Long id) {
       Project p = projectRepository.getOne(id);
      return modelMapper.map(p,ProjectDto.class);

    }

    @Override
    public Page<Project> getAllPageable(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public Boolean delete(Project project) {
        return null;
    }

    @Override
    public List<Project> getByProjectCode(String projectCode) {
        return null;
    }

    @Override
    public List<Project> getByProjectCodeContains(String projectCode) {
        return null;
    }
}

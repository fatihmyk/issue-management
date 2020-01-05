package com.fatihmayuk.issuemanagement.service.impl;

import com.fatihmayuk.issuemanagement.dto.ProjectDto;
import com.fatihmayuk.issuemanagement.entity.Project;
import com.fatihmayuk.issuemanagement.repository.ProjectRepository;
import com.fatihmayuk.issuemanagement.service.ProjectService;
import com.fatihmayuk.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
    public ProjectDto save(ProjectDto projectDto) {

        /* Bu validationları ProjectDto'nun içinde anotasyon olarak yaptığımız için burada gerek kalmadı.
       if (projectDto.getProjectCode()==null){
            throw new IllegalArgumentException("Project code cannot be null!"); } */

        Project projectCheck = (Project)(projectRepository.getByProjectCode(projectDto.getProjectCode()));

        if (projectCheck != null) {
            throw new IllegalArgumentException("Project Code Already Exist"); }

        Project project = modelMapper.map(projectDto, Project.class);
        project = projectRepository.save(project);
        projectDto.setId(project.getId());
        return projectDto;
    }

    @Override
    public ProjectDto getById(Long id) {
        Project project = projectRepository.getOne(id);
        return modelMapper.map(project, ProjectDto.class);
    }

    @Override
    public TPage<ProjectDto> getAllPageable(Pageable pageable) {
        Page<Project> data = projectRepository.findAll(pageable);
        TPage<ProjectDto> respnose = new TPage<ProjectDto>();
        respnose.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), ProjectDto[].class)));
        return respnose;

    }

    @Override
    public Boolean delete(ProjectDto projectDto) {
        return null;
    }

    @Override
    public Boolean delete(Long id){
        projectRepository.deleteById(id);
        return true;
    }

    @Override
    public ProjectDto getByProjectCode(String projectCode) {
        return null;
    }

    @Override
    public List<ProjectDto> getByProjectCodeContains(String projectCode) {
        return null;
    }

    @Override
    public ProjectDto update(Long id, ProjectDto projectDto) {
        Project projectDb=projectRepository.getOne(id);
        if (projectDb == null) {
            throw new IllegalArgumentException("Project Does Not Exist. ID: "+id); }

        Project projectCheck = projectRepository.getByProjectCodeAndIdNot(projectDto.getProjectCode(), id);
        if (projectCheck != null) {
            throw new IllegalArgumentException("Project Code Already Exist");
        }
        projectDb.setProjectCode(projectDto.getProjectCode());
        projectDb.setProjectName(projectDto.getProjectName());

        projectRepository.save(projectDb);
        return modelMapper.map(projectDb,ProjectDto.class);

    }
}

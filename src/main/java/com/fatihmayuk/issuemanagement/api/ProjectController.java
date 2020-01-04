package com.fatihmayuk.issuemanagement.api;

import com.fatihmayuk.issuemanagement.dto.ProjectDto;
import com.fatihmayuk.issuemanagement.service.impl.ProjectServiceImpl;
import com.fatihmayuk.issuemanagement.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *
 * @author:FthMyk
 *
 *
 */

@RestController
@RequestMapping(ApiPaths.ProjectCtrl.CTRL)
@Api(value = ApiPaths.ProjectCtrl.CTRL, description = "Project APIs")
public class ProjectController {

    private final ProjectServiceImpl projectServiceImpl;

    public ProjectController(ProjectServiceImpl projectServiceImpl) {
        this.projectServiceImpl = projectServiceImpl;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation",response = ProjectDto.class)
    public ResponseEntity<ProjectDto> getById(@PathVariable(value = "id",required = true) Long id) {
        ProjectDto projectDto = projectServiceImpl.getById(id);
        return ResponseEntity.ok(projectDto);
    }

    @PostMapping
    @ApiOperation(value = "Create Operation",response = ProjectDto.class)
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectDto projectDto){

        return ResponseEntity.ok(projectServiceImpl.save(projectDto));
    }

    //@RequestMapping(path = "/{id}",method = RequestMethod.PUT)
    @PutMapping("/{id}")
    @ApiOperation(value = "Update Operation",response = ProjectDto.class)
    public ResponseEntity<ProjectDto> updateProject(@PathVariable(value = "id",required = true) Long id,@Valid @RequestBody ProjectDto projectDto){

       return ResponseEntity.ok(projectServiceImpl.update(id,projectDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation",response = Boolean.class)
    public ResponseEntity<Boolean> deleteProject(@PathVariable(value = "id",required = true) Long id){


        return ResponseEntity.ok(projectServiceImpl.delete(id));
    }



}

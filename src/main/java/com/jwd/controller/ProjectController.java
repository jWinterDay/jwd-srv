package com.jwd.controller;

import com.jwd.model.project.Project;
import com.jwd.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class ProjectController {
    @Autowired
    //@Resource
    IProjectService projectService;

    @GetMapping(value = "/project")
    public String getProject() {
        return projectService.findRandom();
    }

    @GetMapping(value = "/project/{id:[\\d]+}")
    public ResponseEntity<?> getProjectById(@PathVariable("id") long projectId) {
        Project project = projectService.findById(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping(value = "/projectfullinfo/{id:[\\d]+}")
    public ResponseEntity<?> findFullInfoById(@PathVariable("id") long projectId) {
        return new ResponseEntity<>(projectService.findFullInfoById(projectId), HttpStatus.OK);
    }
}

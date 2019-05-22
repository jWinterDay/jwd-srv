package com.jwd.controller;

import com.jwd.model.auth.AccessGroupId;
import com.jwd.model.project.Project;
import com.jwd.security.JwtTokenProvider;
import com.jwd.security.RequestAccess;
import com.jwd.service.project.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProjectController {
    @Autowired
    IProjectService projectService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    //view
    @GetMapping(value = "/project_list/{page:[\\d]+}/{size:[\\d]+}")
    public String getProjectListView(@PathVariable("page") int page,
                                     @PathVariable("size") int size,
                                     Model model) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Project> projects = projectService.findAll(pageRequest).getContent();
        model.addAttribute("projects", projects );

        return "project_list";
    }

    @GetMapping(value = "/project_list")
    @RequestAccess(accessGroupId = AccessGroupId.PROJECT_VIEW)
    public String getProjectListView1(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "size", defaultValue = "10") int size,
                                      Model model) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Project> projects = projectService.findAll(pageRequest).getContent();
        model.addAttribute("projects", projects );

        return "project_list";
    }

    @GetMapping(value = "/project/{id:[\\d]+}")
    @ResponseBody
    public ResponseEntity<?> getProjectById(@PathVariable("id") long projectId) {
        Project project = projectService.findById(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping(value = "/projectfullinfo/{id:[\\d]+}")
    @ResponseBody
    public ResponseEntity<?> findFullInfoById(@PathVariable("id") long projectId) {
        return new ResponseEntity<>(projectService.findFullInfoById(projectId), HttpStatus.OK);
    }
}

package com.jwd.service;

import com.jwd.model.project.IProjectInfo;
import com.jwd.model.project.Project;
import com.jwd.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProjectServiceImpl implements IProjectService {
    @Autowired
    private ProjectRepository projectRepository;


    public long getCount() {
        return projectRepository.count();
    }

    public Project findById(Long projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }

    @Override
    public IProjectInfo findFullInfoById(Long projectId) {
        return projectRepository.findFullInfoById(projectId);
    }

    @Override
    public Page<Project> findAll(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public Page<Project> findAllByUserId(Long administratorUserId, Pageable pageable) {
        return projectRepository.findAllByUserId(administratorUserId, pageable);
    }

    @Override
    public String findRandom() {
        Project randomProject = projectRepository.findAll().get(0);
        return randomProject.toString();
    }

    @Override
    public int modifyById(String name, String note, Long projectId) {
        return projectRepository.modifyById(name, note, projectId);
    }

    @Override
    public void deleteById(Long projectId) {
        projectRepository.deleteById(projectId);
    }
}
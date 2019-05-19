package com.jwd.service.project;

import com.jwd.model.project.IProjectInfo;
import com.jwd.model.project.Project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProjectService {
    public Page<Project> findAll(Pageable pageable);
    Page<Project> findAllByUserId(Long administratorUserId, Pageable pageable);
    public Project findById(Long projectId);
    IProjectInfo findFullInfoById(Long projectId);

    public String findRandom();

    int modifyById(String name, String note, Long projectId);

    void deleteById(Long projectId);
}

package com.jwd.repository;

import com.jwd.model.project.IProjectInfo;
import com.jwd.model.project.Project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IProjectRepository extends JpaRepository<Project, Long> {
    //==--find--==//
    @Query("select p from Project p order by name")
    Page<Project> findAll(Pageable pageable);

    @Query("select p from Project p where administratorUserId = ?1")
    Page<Project> findAllByUserId(Long administratorUserId, Pageable pageable);

    @Query("select p.projectId as projectId, p.name as name, p.note as note, p.beginDate as beginDate," +
            " p.endDate as endDate, p.projectGuid as projectGuid," +
            " p.administratorUserId as administratorUserId, u.email as email" +
            " from Project p" +
            " join User u on p.administratorUserId = u.userId" +
            " where p.projectId = ?1")
    IProjectInfo findFullInfoById(Long projectId);

    //==--update--==//
    @Transactional(timeout = 10)
    @Modifying
    @Query("update Project set name = ?1, note = ?2 where projectId = ?3")
    int modifyById(String name, String note, Long projectId);

    //==--delete--==//
    @Transactional(timeout = 10)
    @Modifying
    @Query("delete from Project where projectId = ?1")
    void deleteById(Long projectId);

    @Transactional(timeout = 10)
    @Modifying
    @Query("delete from Project where administratorUserId = ?1")
    void deleteByUserId(Long administratorUserId);

    //==--create--==//
}

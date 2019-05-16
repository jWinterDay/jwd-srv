package com.jwd.model.project;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "project_id")
    private Long projectId;

    private String name;
    private String note;

    @Column(name = "begin_date")
    private Date beginDate;
    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "project_guid")
    private String projectGuid;

    @Column(name = "administrator_user_id", nullable = false)
    private Long administratorUserId;



    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getProjectGuid() {
        return projectGuid;
    }

    public void setProjectGuid(String projectGuid) {
        this.projectGuid = projectGuid;
    }

    public Long getAdministratorUserId() {
        return administratorUserId;
    }

    public void setAdministratorUserId(Long administratorUserId) {
        this.administratorUserId = administratorUserId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append("projectId: " + projectId);
        sb.append(", name: " + name);
        sb.append(", note: " + note);
        sb.append(", projectGuid: " + projectGuid);
        sb.append(", beginDate: " + beginDate);
        sb.append(", endDate: " + endDate);
        sb.append(", administratorUserId: " + administratorUserId);
        sb.append(")");


        return sb.toString();
    }
}

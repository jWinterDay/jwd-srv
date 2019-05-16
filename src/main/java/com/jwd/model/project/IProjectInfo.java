package com.jwd.model.project;

import java.util.Date;

public interface IProjectInfo {
    public Long getProjectId();

    public String getName();

    public String getNote();

    public Date getBeginDate();

    public Date getEndDate();

    public String getProjectGuid();

    public Long getAdministratorUserId();

    public String getEmail();
}

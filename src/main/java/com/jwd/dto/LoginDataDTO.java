package com.jwd.dto;

import com.jwd.model.auth.AccessGroup;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class LoginDataDTO {
    @ApiModelProperty(position = 0)
    private String email;
    @ApiModelProperty(position = 1)
    List<AccessGroup> accessGroups;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AccessGroup> getAccessGroups() {
        return accessGroups;
    }

    public void setAccessGroups(List<AccessGroup> accessGroups) {
        this.accessGroups = accessGroups;
    }
}

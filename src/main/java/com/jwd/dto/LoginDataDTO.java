package com.jwd.dto;

import com.jwd.model.auth.Role;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class LoginDataDTO {
    @ApiModelProperty(position = 0)
    private String email;
    @ApiModelProperty(position = 1)
    List<Role> roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}

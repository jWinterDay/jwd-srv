package com.jwd.dto;

import com.jwd.model.auth.AccessGroup;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class LoginResponseDTO {

    @ApiModelProperty(position = 0)
    private Integer userId;
    @ApiModelProperty(position = 1)
    private String firstName;
    @ApiModelProperty(position = 2)
    private String lastName;
    @ApiModelProperty(position = 3)
    private String email;
    @ApiModelProperty(position = 4)
    List<AccessGroup> accessGroups;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "LoginResponseDTO{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", accessGroups=" + accessGroups +
                '}';
    }
}
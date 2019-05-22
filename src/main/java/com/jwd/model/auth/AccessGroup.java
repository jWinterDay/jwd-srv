package com.jwd.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "access_group")
public class AccessGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "access_group_id_seq")
    @SequenceGenerator(name = "access_group_id_seq", sequenceName = "access_group_id_seq", allocationSize = 1)
    @Column(nullable = false, name = "access_group_id")
    @JsonProperty("access_group_id")
    private Long accessGroupId;

    @Column(name = "name")
    private String name;

    //@Transient
    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "begin_date")
    private Date beginDate;

    //@Transient
    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date")
    private Date endDate;

    //@ManyToMany(mappedBy = "accessGroups")
    //private List<User> userList = new ArrayList<>();

    public AccessGroup() {}

    public AccessGroup(Long accessGroupId, String name) {
        this.accessGroupId = accessGroupId;
        this.name = name;
    }

    public AccessGroup(Long accessGroupId, String name, Date beginDate, Date endDate) {
        this.accessGroupId = accessGroupId;
        this.name = name;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public Long getAccessGroupId() {
        return accessGroupId;
    }

    public void setAccessGroupId(Long accessGroupId) {
        this.accessGroupId = accessGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    /*public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }*/

    @Override
    public String toString() {
        return "AccessGroup{" +
                "accessGroupId=" + accessGroupId +
                ", name=" + name +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                '}';
    }
}

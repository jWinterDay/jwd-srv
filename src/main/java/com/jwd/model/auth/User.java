package com.jwd.model.auth;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "begin_date")
    private Date beginDate;
    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "refresh_token")
    private Long refreshToken;



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(Long refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append("userId: " + userId);
        sb.append(", firstName: " + firstName);
        sb.append(", lastName: " + lastName);
        sb.append(", email: " + email);
        sb.append(", beginDate: " + beginDate);
        sb.append(", endDate: " + endDate);
        sb.append(", refreshToken: " + refreshToken);
        sb.append(")");


        return sb.toString();
    }
}

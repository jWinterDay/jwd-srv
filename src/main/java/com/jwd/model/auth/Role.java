package com.jwd.model.auth;

//import org.springframework.security.core.GrantedAuthority;

public class Role {//implements GrantedAuthority {
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }

    /*ROLE_ADMIN,
    ROLE_CLIENT;

    @Override
    public String getAuthority() {
        return name();
    }*/
}

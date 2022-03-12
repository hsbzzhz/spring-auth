package com.hogan.springadmin.entity;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@ToString
public class UserDO {

    private int id;

    private String username;

    private String password;

    private String salt;

    private Set<String> roles;

    public UserDO(int id, String username, String password, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = "test";
        this.roles = roles;
    }

}

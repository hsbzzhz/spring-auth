package com.hogan.springadmin.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RoleDO {

    private String rolename;
    private String data;

    public RoleDO(String rolename, String data) {
        this.rolename = rolename;
        this.data = data;
    }

}

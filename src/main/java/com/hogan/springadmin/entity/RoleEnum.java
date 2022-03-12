package com.hogan.springadmin.entity;

public enum RoleEnum {

    ADMIN("admin", 1), SALE("sale", 2), GUEST("guest", 3);

    private String name;
    private int index;

    RoleEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

}

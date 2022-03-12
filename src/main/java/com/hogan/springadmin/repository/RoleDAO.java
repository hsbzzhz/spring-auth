package com.hogan.springadmin.repository;

import com.hogan.springadmin.entity.RoleDO;
import com.hogan.springadmin.entity.UserDO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class RoleDAO {

    private final List<RoleDO> arr = new ArrayList<>();

    public RoleDAO() {
        arr.add(new RoleDO("admin", "123"));
        arr.add(new RoleDO("sals", "123"));
        arr.add(new RoleDO("guest", "123"));
    }

    public List<RoleDO> findAll() {
        // test
        return arr;
    }

    public RoleDO findByUsername(String rolename) throws RuntimeException{
        for (RoleDO role : arr) {
            if (Objects.equals(rolename, role.getRolename())) {
                return role;
            }
        }
        throw new RuntimeException("未找到");
    }

    public boolean insert(RoleDO RoleDO) throws RuntimeException {
        for (RoleDO role : arr) {
            if (Objects.equals(RoleDO.getRolename(), role.getRolename())) {
                throw new RuntimeException("角色重复创建");
            }
        }
        arr.add(RoleDO);
        return true;
    }

    public boolean delete(String rolename) {
        for (RoleDO role : arr) {
            if (Objects.equals(rolename, role.getRolename())) {
                arr.remove(role);
                return true;
            }
        }
        return false;
    }

}

package com.hogan.springadmin.controller;

import com.hogan.springadmin.entity.RoleDO;
import com.hogan.springadmin.repository.RoleDAO;
import com.hogan.springadmin.service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    AccessService accessService;

    @GetMapping("/api/roles")
    public List<RoleDO> getRoles() throws RuntimeException {
        accessService.checkLogin();
        return roleDAO.findAll();
    }

    @PostMapping("/api/role")
    public Boolean addRole(@RequestBody RoleDO role) throws RuntimeException {
        accessService.checkLoginAndRoles(new HashSet<>(Arrays.asList("admin")));
        return roleDAO.insert(role);
    }

    @DeleteMapping("/api/role/{rolename}")
    public Boolean deleteRole(@PathVariable String rolename) throws RuntimeException {
        accessService.checkLoginAndRoles(new HashSet<>(Arrays.asList("admin")));
        return roleDAO.delete(rolename);
    }
}

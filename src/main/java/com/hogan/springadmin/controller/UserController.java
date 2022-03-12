package com.hogan.springadmin.controller;


import com.hogan.springadmin.entity.RoleDO;
import com.hogan.springadmin.service.AccessService;
import com.hogan.springadmin.service.UserService;
import com.hogan.springadmin.entity.UserDO;
import com.hogan.springadmin.repository.UserDAO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    UserService userService;

    @Autowired
    AccessService accessService;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/api/users")
    public List<UserDO> getUsers() throws RuntimeException {
        accessService.checkLogin();
        return userDAO.findAll();
    }

    @PostMapping("/api/user")
    public Boolean addUser(@RequestBody UserDO user) throws RuntimeException {
        accessService.checkLoginAndRoles(new HashSet<>(Arrays.asList("admin")));
        return userDAO.insert(user);
    }


    @PutMapping("/api/permission/{username}")
    public Boolean addRoleUser(@RequestBody RoleDO role, @PathVariable String username) throws RuntimeException {
        accessService.checkLoginAndRoles(new HashSet<>(Arrays.asList("admin")));
        return userService.addRoleByUser(userDAO.findByUsername(username), role.getRolename());
    }

    @DeleteMapping("/api/user/{id}")
    public Boolean deleteUser(@PathVariable int id) throws RuntimeException {
        accessService.checkLoginAndRoles(new HashSet<>(Arrays.asList("admin")));
        return userDAO.delete(id);
    }

    @PostMapping("/api/user/login")
    public String login(@RequestBody UserDO userdo) throws RuntimeException {
        UserDO user = userDAO.findByUsername(userdo.getUsername());

        if (!userService.checkUserPassword(user, userdo.getPassword())) {
            throw new RuntimeException("error, 登陆错误");
        }

        return userService.genJwt(user);
    }

    @GetMapping("/api/user/info")
    public UserDO getInfo() throws RuntimeException {
        return accessService.checkLogin();
    }

}

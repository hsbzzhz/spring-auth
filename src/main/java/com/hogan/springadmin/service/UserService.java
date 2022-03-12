package com.hogan.springadmin.service;

import com.hogan.springadmin.entity.RoleDO;
import com.hogan.springadmin.entity.UserDO;
import com.hogan.springadmin.repository.RoleDAO;
import com.hogan.springadmin.utils.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
@Slf4j
public class UserService {
    @Autowired
    private RoleDAO roleDAO;

    public boolean checkUserPassword(UserDO user, String pwd) {
        String myPwd = Utils.genPwd(pwd, user.getSalt());
        return myPwd.equals(user.getPassword());
    }

    public String genJwt(UserDO user) {
        Key key = Keys.hmacShaKeyFor("abcd1234ffgghabcd1234ffgghabcd1234ffgghabcd1234ffgghabcd1234ffggh".getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.claims();
        claims.put("userId", user.getId());
        claims.put("userName", user.getUsername());

        Date expireDate = new Date(System.currentTimeMillis() + 7200000);   // 2小时

        String jws = Jwts.builder().setSubject("test").setClaims(claims).setExpiration(expireDate).signWith(key).compact();
        log.debug(jws);
        return jws;
    }

    public boolean addRoleByUser(UserDO user, String rolename) {
        // 只能添加role 列表里的内容
        List<RoleDO> allRoles = roleDAO.findAll();
        for (RoleDO each : allRoles) {
            if (Objects.equals(each.getRolename(), rolename)) {
                user.getRoles().add(rolename);
                return true;
            }
        }
        throw new RuntimeException("该role 类型不存在");
    }

    public boolean checkHasRole(UserDO user, String role) {
        Set<String> roles = user.getRoles();
        for (String each : roles) {
            if (Objects.equals(each, role)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5DigestAsHex("1234".getBytes()));
    }

}

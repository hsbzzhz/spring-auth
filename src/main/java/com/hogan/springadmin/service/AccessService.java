package com.hogan.springadmin.service;

import com.hogan.springadmin.entity.UserDO;
import com.hogan.springadmin.repository.UserDAO;
import com.hogan.springadmin.utils.JwtUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Set;

@Component
@Slf4j
public class AccessService {

    @Autowired
    HttpServletRequest request;
    @Autowired
    UserDAO userDAO;

    public UserDO checkLogin() throws RuntimeException {
        String jwt = request.getHeader("authorization");
        log.info(jwt);
        // TODO
        Key key = Keys.hmacShaKeyFor("abcd1234ffgghabcd1234ffgghabcd1234ffgghabcd1234ffgghabcd1234ffggh".getBytes(StandardCharsets.UTF_8));
        Jws<Claims> claimsJws;
        try {claimsJws = Jwts.parserBuilder()  // (1)
                    .setSigningKey(key)         // (2)
                    .build()                    // (3)
                    .parseClaimsJws(jwt);// (4)

        } catch (Exception e) {
            throw new RuntimeException("no login");
        }
        String username = (String) claimsJws.getBody().get("userName");
        UserDO user = userDAO.findByUsername(username);
        return user;
    }

    public UserDO checkLoginAndRoles(Set<String> roles) throws RuntimeException {
        String jwt = request.getHeader("authorization");
        log.info(jwt);
        // TODO
        Key key = Keys.hmacShaKeyFor("abcd1234ffgghabcd1234ffgghabcd1234ffgghabcd1234ffgghabcd1234ffggh".getBytes(StandardCharsets.UTF_8));
        Jws<Claims> claimsJws;
        try {claimsJws = Jwts.parserBuilder()  // (1)
                .setSigningKey(key)         // (2)
                .build()                    // (3)
                .parseClaimsJws(jwt);// (4)

        } catch (Exception e) {
            throw new RuntimeException("no login");
        }
        String username = (String) claimsJws.getBody().get("userName");
        UserDO user = userDAO.findByUsername(username);

        // 检查roles
        for (String role : user.getRoles()) {
            if (roles.contains(role)) {
                return user;
            }
        }

        throw new RuntimeException("no access");
    }

}

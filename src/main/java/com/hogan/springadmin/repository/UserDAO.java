package com.hogan.springadmin.repository;

import com.hogan.springadmin.entity.UserDO;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDAO {

    private final List<UserDO> arr = new ArrayList<>();

    public UserDAO() {
        // secret == 1234
        arr.add(new UserDO(1, "aaa", "81dc9bdb52d04dc20036dbd8313ed055", new HashSet<>(Arrays.asList("admin", "sale", "guest"))));
        arr.add(new UserDO(2, "bbb", "81dc9bdb52d04dc20036dbd8313ed055", new HashSet<>(Arrays.asList("admin", "sale"))));
        arr.add(new UserDO(3, "ccc", "81dc9bdb52d04dc20036dbd8313ed055", new HashSet<>(Arrays.asList("guest"))));
    }

    public List<UserDO> findAll() {
        // test
        return arr;
    }

    public UserDO findByUsername(String username) throws RuntimeException {
        for (UserDO user : arr) {
            if (Objects.equals(username, user.getUsername())) {
                return user;
            }
        }
        throw new RuntimeException("未找到");
    }

    public boolean insert(UserDO userDO) throws RuntimeException{
        for (UserDO user : arr) {
            if (Objects.equals(userDO.getUsername(), user.getUsername())) {
                throw new RuntimeException("用户重复创建");
            }
        }
        arr.add(userDO);
        return true;
    }


    public boolean delete(int id) {
        for (UserDO user : arr) {
            if (id == user.getId()) {
                arr.remove(user);
                return true;
            }
        }
        return false;
    }

}

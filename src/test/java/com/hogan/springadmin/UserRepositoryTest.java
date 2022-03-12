package com.hogan.springadmin;

import com.hogan.springadmin.entity.UserDO;
import com.hogan.springadmin.repository.UserDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserDAO userDAO = new UserDAO();


    @Test
    public void findAllUsers(){
        List<UserDO> list= userDAO.findAll();
        for (UserDO each : list) {
            System.out.println(each);
        }
    }

}

package com.hogan.springadmin.utils;

import org.springframework.util.DigestUtils;

public class Utils {

    public static String genPwd(String rawPwd, String salt){
        // fixme add salt
        return DigestUtils.md5DigestAsHex(rawPwd.getBytes());
    }

}

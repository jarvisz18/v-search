package cn.ixan.search.java8;

import java.io.Serializable;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2020/2/7 14:16
 * @description user entity
 */
public class User implements Serializable {
    public static String username;
    private transient String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        User.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}




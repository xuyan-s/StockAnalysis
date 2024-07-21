package com.xuyan.stock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class TestPasswordEncoder {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testPasswordEncoder() {
        String password = "123456";
        String encode = passwordEncoder.encode(password);
        System.out.println(encode);
    }

    @Test
    public void testMatches() {
        String password = "123456";
        String encode = passwordEncoder.encode(password);
        boolean matches = passwordEncoder.matches(password, encode);
        System.out.println(matches ? "密码匹配" : "密码不匹配");
    }
}

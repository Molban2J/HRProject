package com.hr.security;

import com.hr.domain.Admin;
import com.hr.mapper.AdminMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class SecurityTest {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    AdminMapper adminMapper;

    @Test
    void addAdminTest(){
        Admin admin = new Admin();
        admin.setId("admin");
        String password = "admin";
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        log.info("password = "+password);
        log.info("encoded = "+encodedPassword);
        admin.setPassword(encodedPassword);
        admin.setRole("ADMIN");
        adminMapper.addAdmin(admin);

    }
}

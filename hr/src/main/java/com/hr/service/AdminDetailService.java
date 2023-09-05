package com.hr.service;

import com.hr.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminDetailService implements UserDetailsService {
    final
    AdminMapper adminMapper;
    @Autowired
    public AdminDetailService(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return Optional
                .ofNullable(adminMapper.findUserById(id))
                .orElseThrow(()-> new BadCredentialsException("아이디를 확인해주세요."));
    }
}

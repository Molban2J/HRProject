package com.hr.security;

import com.hr.domain.Admin;
import com.hr.service.AdminDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final AdminDetailService adminDetailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String id = token.getName();
        String password = (String) token.getCredentials();

        Admin admin = (Admin) adminDetailService.loadUserByUsername(id);
        if(!bCryptPasswordEncoder.matches(password, admin.getPassword())){
            throw new BadCredentialsException(admin.getId()+" 비밀번호를 확인해주세요");
        }
        return new UsernamePasswordAuthenticationToken(admin,password,admin.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

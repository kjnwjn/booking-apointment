package com.quanpham.demo.services.impl;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.quanpham.demo.models.Role;
import com.quanpham.demo.models.User;
import com.quanpham.demo.repository.RoleRepository;
import com.quanpham.demo.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UsernamePwdProvider implements AuthenticationProvider {

    private final UserRepository userRepo;
    private final PasswordEncoder passEncoder;
    private final RoleRepository roleRepo;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String pwd = authentication.getCredentials().toString();
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("User not found!"));
        if (passEncoder.matches(pwd, user.getPassword())) {
            List<Role> roles = roleRepo.findByUserId(user.getId());

            List<SimpleGrantedAuthority> authorities = roles.stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .toList();
            return UsernamePasswordAuthenticationToken.authenticated(user, null, authorities);
        }
        throw new BadCredentialsException("Wrong password!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }

}

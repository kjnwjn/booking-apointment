package com.quanpham.demo.config.provider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import com.quanpham.demo.models.BankAdmin;
import com.quanpham.demo.models.Roles;
import com.quanpham.demo.repository.IBankAdminData;
import com.quanpham.demo.repository.IRolesData;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthService implements AuthenticationProvider {

    private final IBankAdminData userRepo;

    private final IRolesData roleRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getPrincipal().toString();
        String pwd = authentication.getCredentials().toString();

        BankAdmin user = userRepo.findByEmail(email).orElseThrow(() -> new BadCredentialsException("User not found!"));

        if (passwordEncoder.matches(pwd, user.getPassword())) {

            List<Roles> roles = roleRepo.findByUserId(user.getId());
            return UsernamePasswordAuthenticationToken.authenticated(user, "", roles);
        }
        throw new BadCredentialsException("Invalid password!");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }

}

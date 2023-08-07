package com.quanpham.demo.config.filters;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.quanpham.demo.models.BankAdmin;
import com.quanpham.demo.models.Roles;
import com.quanpham.demo.repository.IBankAdminData;
import com.quanpham.demo.repository.IRolesData;
import com.quanpham.demo.utils.JwtUtils;

import io.jsonwebtoken.Claims;

// @AllArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private IBankAdminData userRepo;

    @Autowired
    private IRolesData roleRepo;

    // public JwtFilter(){
    // super();
    // }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7, token.length());
            if (JwtUtils.validateJwtToken(token)) {
                Claims claims = JwtUtils.decodeJwtToken(token);
                String email = claims.get("user", String.class);
                BankAdmin user = userRepo.findByEmail(email)
                        .orElseThrow(() -> new BadCredentialsException("User with email not found!"));

                List<Roles> roles = roleRepo.findByUserId(user.getId());
                SecurityContextHolder.getContext()
                        .setAuthentication(UsernamePasswordAuthenticationToken.authenticated(user, "", roles));
            }
        }
        filterChain.doFilter(request, response);
    }
}

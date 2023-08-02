package com.quanpham.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quanpham.demo.BaseRespone.request.AuthRequest;
import com.quanpham.demo.BaseRespone.response.BaseResponse;
import com.quanpham.demo.models.BankAdmin;
import com.quanpham.demo.models.Roles;
import com.quanpham.demo.repository.IBankAdminData;
import com.quanpham.demo.repository.IRolesData;
import com.quanpham.demo.utils.JwtUtils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IBankAdminData userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IRolesData roleRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> authenticateUser(@RequestBody AuthRequest loginDto) {

        BaseResponse response = new BaseResponse();

        Map<String, Object> claims = new HashMap<>();
        Optional<BankAdmin> user = userRepo.findByEmail(loginDto.getEmail());

        if (user.isPresent()) {

            if (passwordEncoder.matches(loginDto.getPassword(), user.get().getPassword())) {

                List<Roles> roles = roleRepo.findByUserId(user.get().getId());
                Authentication authentication = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(
                                loginDto.getEmail(), user.get().getPassword(), roles));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                claims.put("email", user.get().getEmail());
                claims.put("roles", roles);
                response.setData(JwtUtils.genJwt(claims));
                response.setErrorCode("0");
                response.setErrorDesc("Thành công");

            } else {
                response.setErrorCode("1");
                response.setErrorDesc("Invalid password");
            }
        } else {
            response.setErrorCode("2");
            response.setErrorDesc("User not found");
        }
        return ResponseEntity.ok(response);

    }

    @GetMapping("/token")
    public ResponseEntity<BaseResponse> getToken() {
        BaseResponse response = new BaseResponse();
        Map<String, Object> claims = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof BankAdmin) {
            BankAdmin user = (BankAdmin) auth.getPrincipal();
            claims.put("email", user.getEmail());
            claims.put("roles", auth.getAuthorities());
            response.setData(JwtUtils.genJwt(claims));
            response.setErrorCode("0");
            response.setErrorDesc("Thành công");
            return ResponseEntity.ok(response);

        } else {
            response.setErrorCode("401");
            response.setErrorDesc("Thất bại");
        }

        return ResponseEntity.ok(response);

    }
}

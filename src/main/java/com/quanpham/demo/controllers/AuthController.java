package com.quanpham.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
import com.quanpham.demo.BaseRespone.response.BankAdminResponse;
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
        Map<String, Object> tokenData = new HashMap<>();

        BankAdmin user = userRepo.findByEmail(loginDto.getEmail()).get();

        if (user != null) {

            if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {

                List<Roles> roles = roleRepo.findByUserId(user.getId());
                Authentication authentication = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(
                                loginDto.getEmail(), user.getPassword(), roles));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                BankAdminResponse BankAdminResponse = new BankAdminResponse(user.getId(), user.getEmail(),
                        user.getFirstName(), user.getLastName(), user.getPhone(), user.getStatus(), user.getRate(),
                        roles);
                claims.put("roles", roles);
                claims.put("email", user.getEmail());

                tokenData.put("user", BankAdminResponse);
                tokenData.put("token", JwtUtils.genJwt(claims));

                response.setData(tokenData);
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

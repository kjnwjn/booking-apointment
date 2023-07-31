package com.quanpham.demo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quanpham.demo.BaseRespone.response.BaseResponse;
import com.quanpham.demo.models.BankAdmin;
import com.quanpham.demo.utils.JwtUtils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("token")
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

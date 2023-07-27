package com.quanpham.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quanpham.demo.BaseRespone.request.AuthRequest;
import com.quanpham.demo.BaseRespone.request.BankAdminRequest;
import com.quanpham.demo.BaseRespone.response.AuthResponse;
import com.quanpham.demo.BaseRespone.response.BaseResponse;
import com.quanpham.demo.services.IBankAdminService;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private final AuthenticationService service;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}

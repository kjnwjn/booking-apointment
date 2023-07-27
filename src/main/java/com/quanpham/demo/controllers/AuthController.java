// package com.quanpham.demo.controllers;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.quanpham.demo.baserequest.request.AuthRequest;
// import com.quanpham.demo.baserequest.response.AuthResponse;
// import com.quanpham.demo.services.IAuthService;

// @CrossOrigin("*")
// @RestController
// @RequestMapping("/auth")
// public class AuthController {
// @Autowired
// private final IAuthService service;

// @PostMapping("/login")
// public ResponseEntity<AuthResponse> authenticate(
// @RequestBody AuthRequest request) {
// return ResponseEntity.ok(service.authenticate(request));
// }

// }

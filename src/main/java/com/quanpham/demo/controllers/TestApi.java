package com.quanpham.demo.controllers;

import javax.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestApi {
    @RolesAllowed("ADMIN")
    @GetMapping
    public String test() {
        return "TestApi []";
    }
}

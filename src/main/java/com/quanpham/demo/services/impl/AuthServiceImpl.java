package com.quanpham.demo.services.impl;

import org.springframework.stereotype.Service;

import com.quanpham.demo.BaseRespone.request.AuthRequest;
import com.quanpham.demo.BaseRespone.response.BaseResponse;
import com.quanpham.demo.services.IAuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    @Override
    public BaseResponse authenticate(AuthRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
    }

}

package com.quanpham.demo.services;

import com.quanpham.demo.BaseRespone.request.AuthRequest;
import com.quanpham.demo.BaseRespone.response.BaseResponse;

public interface IAuthService {

    BaseResponse authenticate(AuthRequest request);

}

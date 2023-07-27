package com.quanpham.demo.services;

import com.quanpham.demo.baserequest.request.AuthRequest;
import com.quanpham.demo.baserequest.response.BaseResponse;

public interface IAuthService {

    BaseResponse authenticate(AuthRequest request);

}

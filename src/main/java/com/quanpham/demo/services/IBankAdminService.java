package com.quanpham.demo.services;

import javax.validation.Valid;

import com.quanpham.demo.baserequest.request.BankAdminRequest;
import com.quanpham.demo.baserequest.response.BaseResponse;

public interface IBankAdminService {

    BaseResponse getAllBankAdmin();

    BaseResponse getBankAdminById(Long id);

    BaseResponse create(BankAdminRequest product);

}
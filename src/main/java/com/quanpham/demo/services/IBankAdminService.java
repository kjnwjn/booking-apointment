package com.quanpham.demo.services;

import javax.validation.Valid;

import com.quanpham.demo.BaseRespone.request.BankAdminRequest;
import com.quanpham.demo.BaseRespone.response.BaseResponse;

public interface IBankAdminService {

    BaseResponse getAllBankAdmin();

    BaseResponse getBankAdminById(Long id);

    BaseResponse create(BankAdminRequest product);

}
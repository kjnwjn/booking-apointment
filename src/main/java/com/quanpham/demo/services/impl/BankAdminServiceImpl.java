package com.quanpham.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quanpham.demo.baserequest.request.BankAdminRequest;
import com.quanpham.demo.baserequest.response.BaseResponse;
import com.quanpham.demo.models.BankAdmin;
import com.quanpham.demo.models.Product;
import com.quanpham.demo.repository.IBankAdminData;
import com.quanpham.demo.services.IBankAdminService;

@Service
public class BankAdminServiceImpl implements IBankAdminService {

    @Autowired
    private IBankAdminData bankAdminData;

    @Override
    public BaseResponse getAllBankAdmin() {
        try {
            BaseResponse response = new BaseResponse();

            List<BankAdmin> bankAdminList = this.bankAdminData.findAll();
            if (bankAdminList != null && !bankAdminList.isEmpty()) {
                response.setData(bankAdminList);
                response.setErrorCode("0");
                response.setErrorDesc("Thành công");
            } else {
                response.setErrorCode("1");
                response.setErrorDesc("Thất bại");
            }
            return response;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public BaseResponse getBankAdminById(Long id) {
        BaseResponse response = new BaseResponse();
        Optional<BankAdmin> bankAdmin = this.bankAdminData.findById(id);
        if (bankAdmin != null && bankAdmin.isPresent()) {
            response.setData(bankAdmin);
            response.setErrorCode("0");
            response.setErrorDesc("Thành công");
        } else {
            response.setErrorCode("1");
            response.setErrorDesc("Thất bại");
        }
        return response;
    }

    @Override
    public BaseResponse create(BankAdminRequest product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

}

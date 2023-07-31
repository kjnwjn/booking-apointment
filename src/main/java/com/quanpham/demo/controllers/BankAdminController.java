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

import com.quanpham.demo.BaseRespone.request.BankAdminRequest;
import com.quanpham.demo.BaseRespone.response.BaseResponse;
import com.quanpham.demo.services.IBankAdminService;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class BankAdminController {
    @Autowired
    private IBankAdminService bankAdminService;

    @GetMapping("get-all")
    public ResponseEntity<BaseResponse> getAllBankAdmin() {
        return new ResponseEntity<>(this.bankAdminService.getAllBankAdmin(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getBankAdminById(@PathVariable("id") Long id) {

        return ResponseEntity.ok(this.bankAdminService.getBankAdminById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> addUser(@Valid @RequestBody BankAdminRequest product) {
        return ResponseEntity.ok(this.bankAdminService.create(product));
    }

    // @PutMapping("/{id}")
    // public BaseResponse updateProduct(@RequestBody ProductRequest productReq) {
    // return this.bankAdminService.updateProduct(productReq);
    // }

    // @DeleteMapping("/{id}")
    // public BaseResponse deleteProduct(@PathVariable("id") String id) {
    // return this.bankAdminService.deleteProduct(id);
    // }
}

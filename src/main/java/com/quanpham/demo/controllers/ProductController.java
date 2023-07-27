package com.quanpham.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quanpham.demo.baserequest.request.ProductRequest;
import com.quanpham.demo.baserequest.response.BaseResponse;
import com.quanpham.demo.baserequest.response.ProductResponse;
import com.quanpham.demo.services.IProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("get-all")
    public ResponseEntity<BaseResponse> getAllProducts() {
        return new ResponseEntity<>(this.productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getProductById(@PathVariable("id") String id) {

        return ResponseEntity.ok(this.productService.getProductById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> addProduct(@Valid @RequestBody ProductResponse product) {
        return ResponseEntity.ok(this.productService.create(product));
    }

    @PutMapping("/{id}")
    public BaseResponse updateProduct(@RequestBody ProductRequest productReq) {
        return this.productService.updateProduct(productReq);
    }

    @DeleteMapping("/{id}")
    public BaseResponse deleteProduct(@PathVariable("id") String id) {
        return this.productService.deleteProduct(id);
    }
}

package com.quanpham.demo.services;

import com.quanpham.demo.BaseRespone.request.ProductRequest;
import com.quanpham.demo.BaseRespone.response.BaseResponse;
import com.quanpham.demo.BaseRespone.response.ProductResponse;

public interface IProductService {

    BaseResponse getAllProducts();

    BaseResponse getProductById(String id);

    BaseResponse create(ProductResponse product);

    BaseResponse updateProduct(ProductRequest productReq);

    BaseResponse deleteProduct(String id);

    // BaseResponse getProductByCondition(String idTicket);

}

package com.quanpham.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quanpham.demo.baserequest.request.ProductRequest;
import com.quanpham.demo.baserequest.response.BaseResponse;
import com.quanpham.demo.baserequest.response.ProductResponse;
import com.quanpham.demo.models.Product;
import com.quanpham.demo.models.Ticket;
import com.quanpham.demo.repository.ICategoryData;
import com.quanpham.demo.repository.IProductData;
import com.quanpham.demo.repository.ITicketData;
import com.quanpham.demo.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductData productData;
    @Autowired
    private ICategoryData categoryData;
    @Autowired
    private ITicketData ticketData;

    @Override
    public BaseResponse getAllProducts() {
        BaseResponse response = new BaseResponse();

        List<Product> productList = this.productData.findAll();
        if (productList != null && !productList.isEmpty()) {
            response.setData(productList);
            response.setErrorCode("0");
            response.setErrorDesc("Thành công");
        } else {
            response.setErrorCode("1");
            response.setErrorDesc("Thất bại");
        }
        return response;
    }

    @Override
    public BaseResponse getProductById(String id) {
        BaseResponse response = new BaseResponse();
        Optional<Product> product = this.productData.findById(Long.parseLong(id));
        if (product != null && product.isPresent()) {
            response.setData(product);
            response.setErrorCode("0");
            response.setErrorDesc("Thành công");
        } else {
            response.setErrorCode("1");
            response.setErrorDesc("Thất bại");
        }
        return response;
    }

    @Override
    public BaseResponse create(ProductResponse product) {
        try {
            BaseResponse response = new BaseResponse();

            Optional categoryExist = categoryData.findById(product.getIdCategory());

            if (categoryExist.isEmpty()) {
                response.setErrorCode("1");
                response.setErrorDesc("category không tồn tại");
            } else {
                Product productNew = new Product();
                productNew.setProductName(product.getProductName());
                productNew.setDesc(product.getDesc());
                productNew.setIdCategory(product.getIdCategory());
                Product a = productData.save(productNew);
                response.setData(a);
                response.setErrorCode("0");
                response.setErrorDesc("Thành công");
            }

            return response;
        } catch (Exception e) {
            return null;

        }
    }

    @Override
    public BaseResponse updateProduct(ProductRequest productReq) {
        try {
            BaseResponse response = new BaseResponse();

            Optional categoryExist = categoryData.findById(productReq.getIdCategory());

            Optional<Product> oldProduct = productData.findById(productReq.getIdProduct());
            if (oldProduct == null || !oldProduct.isPresent()) {
                response.setErrorCode("1");
                response.setErrorDesc("Product is not exist");
            } else if (categoryExist == null || !categoryExist.isPresent()) {
                response.setErrorCode("2");
                response.setErrorDesc("category is not exist");
            } else {
                Product productNew = new Product();
                productNew.setIdProduct(productReq.getIdProduct());
                productNew.setProductName(productReq.getProductName());
                productNew.setDesc(productReq.getDesc());
                productNew.setIdCategory(productReq.getIdCategory());
                Product a = productData.save(productNew);
                response.setData(a);
                response.setErrorCode("0");
                response.setErrorDesc("Thành công");
            }

            return response;
        } catch (Exception e) {
            return null;

        }
    }

    @Override
    public BaseResponse deleteProduct(String id) {
        try {

            BaseResponse response = new BaseResponse();
            List<Ticket> ticketExist = ticketData.findByIdProduct(Long.parseLong(id));
            if (!ticketExist.isEmpty()) {
                response.setErrorCode("1");
                response.setErrorDesc(" thất bại");
            } else {
                productData.deleteById(Long.parseLong(id));
                response.setErrorCode("0");
                response.setErrorDesc("Thành công");
            }

            return response;
        } catch (Exception e) {
            return null;

        }
    }

}

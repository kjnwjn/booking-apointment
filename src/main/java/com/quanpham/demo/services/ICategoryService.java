package com.quanpham.demo.services;

import com.quanpham.demo.baserequest.request.CategoryCreateRequest;
import com.quanpham.demo.baserequest.request.CategoryRequest;
import com.quanpham.demo.baserequest.response.BaseResponse;

public interface ICategoryService {

    BaseResponse getAllCategory();

    BaseResponse getCategoryById(String id);

    BaseResponse create(CategoryRequest category);

    BaseResponse updateCategory(CategoryCreateRequest categoryReq);

    BaseResponse deleteCategory(Long id);

}

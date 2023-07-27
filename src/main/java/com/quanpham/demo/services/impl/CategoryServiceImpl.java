package com.quanpham.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quanpham.demo.baserequest.request.CategoryCreateRequest;
import com.quanpham.demo.baserequest.request.CategoryRequest;
import com.quanpham.demo.baserequest.response.BaseResponse;
import com.quanpham.demo.models.Category;
import com.quanpham.demo.repository.ICategoryData;
import com.quanpham.demo.services.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryData categoryData;

    @Override
    public BaseResponse getAllCategory() {
        BaseResponse response = new BaseResponse();

        List<Category> categoryList = this.categoryData.findAll();
        if (categoryList != null && !categoryList.isEmpty()) {
            response.setData(categoryList);
            response.setErrorCode("0");
            response.setErrorDesc("Thành công");
        } else {
            response.setErrorCode("1");
            response.setErrorDesc("Thất bại");
        }
        return response;
    }

    @Override
    public BaseResponse getCategoryById(String id) {
        BaseResponse response = new BaseResponse();
        Optional<Category> category = this.categoryData.findById(Long.parseLong(id));
        if (category != null && category.isPresent()) {
            response.setData(category);
            response.setErrorCode("0");
            response.setErrorDesc("Thành công");
        } else {
            response.setErrorCode("1");
            response.setErrorDesc("Thất bại");
        }
        return response;
    }

    @Override
    public BaseResponse create(CategoryRequest category) {
        try {
            BaseResponse response = new BaseResponse();

            Category categoryNew = new Category();
            categoryNew.setCategoryName(category.getCategoryName());
            categoryNew.setDesc(category.getDesc());
            Category a = categoryData.save(categoryNew);
            response.setData(a);
            response.setErrorCode("0");
            response.setErrorDesc("Thành công");

            return response;
        } catch (Exception e) {
            return null;

        }
    }

    @Override
    public BaseResponse updateCategory(CategoryCreateRequest categoryReq) {
        try {
            BaseResponse response = new BaseResponse();

            Optional categoryExist = categoryData.findById(categoryReq.getId());

            if (categoryExist.isEmpty()) {
                response.setErrorCode("1");
                response.setErrorDesc("category không tồn tại");
            } else {
                Category categoryNew = new Category();
                categoryNew.setId(categoryReq.getId());
                categoryNew.setCategoryName(categoryReq.getCategoryName());
                categoryNew.setDesc(categoryReq.getDesc());
                Category a = categoryData.save(categoryNew);
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
    public BaseResponse deleteCategory(Long id) {
        try {
            BaseResponse response = new BaseResponse();
            Optional<Category> categoryExist = categoryData.findById(id);
            if (categoryExist == null || !categoryExist.isPresent()) {
                response.setErrorCode("1");
                response.setErrorDesc(" thất bại");
            } else {
                categoryData.deleteById(id);
                response.setErrorCode("0");
                response.setErrorDesc("Thành công");
            }

            return response;
        } catch (Exception e) {
            return null;

        }
    }

}

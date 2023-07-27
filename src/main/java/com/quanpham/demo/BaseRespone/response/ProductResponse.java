package com.quanpham.demo.BaseRespone.response;

import javax.validation.constraints.NotBlank;

import com.quanpham.demo.models.Category;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ProductResponse {

    @NotBlank(message = "productName is required")
    private String productName;
    @NotBlank(message = "desc is required")
    private String desc;
    private Long idCategory;
}

package com.quanpham.demo.BaseRespone.request;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class CategoryCreateRequest {

    private Long id;
    @NotBlank(message = "categoryName is required")
    private String categoryName;
    @NotBlank(message = "desc is required")
    @Column(name = "description")
    private String desc;
}

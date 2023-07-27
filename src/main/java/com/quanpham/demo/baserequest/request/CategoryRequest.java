package com.quanpham.demo.baserequest.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class CategoryRequest {

    @NotBlank(message = "categoryName is required")
    private String categoryName;
    @NotBlank(message = "desc is required")
    @Column(name = "description")
    private String desc;
}

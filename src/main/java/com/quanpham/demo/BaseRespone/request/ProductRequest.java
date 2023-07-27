package com.quanpham.demo.BaseRespone.request;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ProductRequest {
    @Id
    @NotBlank(message = "idProduct is required")
    @Column(name = "id")
    private Long idProduct;
    @NotBlank(message = "productName is required")
    private String productName;
    @NotBlank(message = "desc is required")
    private String desc;
    @NotBlank(message = "idCategory is required")
    private Long idCategory;
}

package com.quanpham.demo.BaseRespone.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.quanpham.demo.models.Product;

import lombok.Data;

@Data
public class TicketRequest {
    @NotBlank(message = "customerName is required")
    private String customer_name;
    @NotBlank
    @Email(message = "customerEmail is required")
    private String customer_email;
    @NotNull(message = "product is required")
    private Product product;

}

package com.quanpham.demo.BaseRespone.response;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TicketResponse extends BaseResponse {
    @NotBlank(message = "customerName is required")
    private String customer_name;
    @NotBlank
    @Email(message = "customerEmail is required")
    private String customer_email;

}

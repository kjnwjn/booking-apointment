package com.quanpham.demo.BaseRespone.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.quanpham.demo.enums.StatusTicketEnum;
import com.quanpham.demo.models.AbstractEntity;
import com.quanpham.demo.models.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateTicketRequest extends AbstractEntity {

    @NotBlank(message = "id is required!")
    private Long id;
    private Long idTransCounter;
    private String customerName;
    private String customerEmail;
    @Size(min = 1, max = 5, message = "rate must be between 1 and 5 characters")
    private int rate;
    private StatusTicketEnum.StatusTicket status;
    private Product product;

    @Override
    public void preUpdate() {
        // TODO Auto-generated method stub
        super.preUpdate();
    }

}

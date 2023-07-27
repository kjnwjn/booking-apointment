package com.quanpham.demo.services;

import com.quanpham.demo.BaseRespone.response.BaseResponse;
import com.quanpham.demo.BaseRespone.response.TicketResponse;

public interface ITicketService {

    BaseResponse getAllTickets();

    BaseResponse getTicketById(String id);

    BaseResponse getTicketByIdUser(String idUser);

    TicketResponse create(TicketResponse ticket);

}

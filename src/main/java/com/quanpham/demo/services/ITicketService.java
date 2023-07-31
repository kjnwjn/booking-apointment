package com.quanpham.demo.services;

import com.quanpham.demo.BaseRespone.request.TicketRequest;
import com.quanpham.demo.BaseRespone.response.BaseResponse;
import com.quanpham.demo.models.Ticket;

public interface ITicketService {

    BaseResponse getAllTickets();

    BaseResponse getTicketById(String id);

    BaseResponse getTicketByIdTransCounter(Long idTransCounter);

    BaseResponse create(TicketRequest ticket);

    BaseResponse updateTicket(Ticket request);

}

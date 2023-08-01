package com.quanpham.demo.services;

import com.quanpham.demo.BaseRespone.request.TicketRequest;
import com.quanpham.demo.BaseRespone.request.UpdateTicketRequest;
import com.quanpham.demo.BaseRespone.response.BaseResponse;

public interface ITicketService {

    BaseResponse getAllTickets();

    BaseResponse getTicketById(Long id);

    BaseResponse getTicketByIdTransCounter(Long idTransCounter);

    BaseResponse create(TicketRequest ticket);

    BaseResponse updateTicket(Long id, UpdateTicketRequest request);

    BaseResponse deleteTicket(Long id);

}

package com.quanpham.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quanpham.demo.BaseRespone.response.BaseResponse;
import com.quanpham.demo.BaseRespone.response.TicketResponse;
import com.quanpham.demo.models.Ticket;
import com.quanpham.demo.repository.ITicketData;
import com.quanpham.demo.services.ITicketService;

@Service
public class TicketServiceImpl implements ITicketService {

    @Autowired
    private ITicketData ticketData;

    @Override
    public BaseResponse getAllTickets() {
        BaseResponse response = new BaseResponse();
        List<Ticket> ticketList = ticketData.findAll();

        if (ticketList != null || !ticketList.isEmpty()) {
            response.setData(ticketList);
            response.setErrorCode("0");
            response.setErrorDesc("Thành công");
        } else {
            response.setErrorCode("1");
            response.setErrorDesc("Thất bại");
        }
        return response;

    }

    @Override
    public BaseResponse getTicketById(String id) {
        BaseResponse response = new BaseResponse();
        Optional<Ticket> ticket = this.ticketData.findById(Long.parseLong(id));

        // Optional<Ticket> ticket = this.ticketData.
        if (ticket != null && ticket.isPresent()) {
            response.setData(ticket);
            response.setErrorCode("0");
            response.setErrorDesc("Thành công");
        } else {
            response.setErrorCode("1");
            response.setErrorDesc("Thất bại");
        }
        return response;
    }

    @Override
    public BaseResponse getTicketByIdUser(String idUser) {
        BaseResponse response = new BaseResponse();
        List<Ticket> ticketList = this.ticketData.findByIdUser(idUser);
        if (!ticketList.isEmpty()) {
            response.setData(ticketList);
            response.setErrorCode("0");
            response.setErrorDesc("Thành công");
        } else {
            response.setErrorCode("1");
            response.setErrorDesc("Thất bại");
        }
        return response;
    }

    @Override
    public TicketResponse create(TicketResponse ticket) {
        TicketResponse response = new TicketResponse();
        // Object ticket = this.ticketData.findByIdUser(idUser);
        if (ticket != null) {
            // response.setData(ticket);
            response.setErrorCode("0");
            response.setErrorDesc("Thành công");
        } else {
            response.setErrorCode("1");
            response.setErrorDesc("Thất bại");
        }
        return response;
    }

}

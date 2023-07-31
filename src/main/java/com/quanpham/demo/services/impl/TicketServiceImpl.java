package com.quanpham.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quanpham.demo.BaseRespone.request.TicketRequest;
import com.quanpham.demo.BaseRespone.response.BaseResponse;
import com.quanpham.demo.enums.StatusTicketEnum;
import com.quanpham.demo.models.Ticket;
import com.quanpham.demo.models.TransCounter;
import com.quanpham.demo.repository.ITicketData;
import com.quanpham.demo.repository.ITransCounterData;
import com.quanpham.demo.services.ITicketService;

@Service
public class TicketServiceImpl implements ITicketService {

    @Autowired
    private ITicketData ticketData;

    @Autowired
    private ITransCounterData transCounterData;

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
    public BaseResponse getTicketByIdTransCounter(Long idTransCounter) {
        BaseResponse response = new BaseResponse();
        List<Ticket> ticketList = this.ticketData.findByIdTransCounter(idTransCounter);
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
    public BaseResponse create(TicketRequest ticket) {
        try {
            BaseResponse response = new BaseResponse();
            List<TransCounter> transCounterList = transCounterData.findByIdProduct(ticket.getProduct().getIdProduct());
            if (!transCounterList.isEmpty()) {
                TransCounter transCounter = transCounterList.get(0);
                Ticket ticketNew = new Ticket(transCounter.getId(), ticket.getProduct(), ticket.getCustomer_name(),
                        ticket.getCustomer_email(), StatusTicketEnum.StatusTicket.INITIAL, 1);

                ticketData.save(ticketNew);
                response.setData(ticketNew);
                response.setErrorCode("0");
                response.setErrorDesc("Thành công");
            } else {
                response.setErrorCode("1");
                response.setErrorDesc("Thất bại");
            }
            return response;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    @Override
    public BaseResponse updateTicket(Ticket request) {
        try {
            BaseResponse response = new BaseResponse();
            ticketData.save(request);
            response.setData(request);
            response.setErrorCode("0");
            response.setErrorDesc("Thành công");
            return response;
        } catch (Exception e) {
            // System.out.println(e);
            return null;
        }
    }

}

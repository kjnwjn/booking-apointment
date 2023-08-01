package com.quanpham.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.quanpham.demo.BaseRespone.request.TicketRequest;
import com.quanpham.demo.BaseRespone.request.UpdateTicketRequest;
import com.quanpham.demo.BaseRespone.response.BaseResponse;
import com.quanpham.demo.enums.StatusTicketEnum;
import com.quanpham.demo.models.Ticket;
import com.quanpham.demo.models.TransCounter;
import com.quanpham.demo.repository.ITicketData;
import com.quanpham.demo.repository.ITransCounterData;
import com.quanpham.demo.services.ITicketService;

@EnableAsync
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
    public BaseResponse getTicketById(Long id) {
        BaseResponse response = new BaseResponse();
        Optional<Ticket> ticket = this.ticketData.findById(id);

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

    @Async
    @Override
    public BaseResponse create(TicketRequest ticket) {
        try {
            BaseResponse response = new BaseResponse();
            List<TransCounter> transCounterList = transCounterData.findByIdProduct(ticket.getProduct().getIdProduct());
            if (!transCounterList.isEmpty()) {
                TransCounter transCounter = transCounterList.get(0);

                Ticket ticketNew = new Ticket(transCounter.getId(), ticket.getProduct(), ticket.getCustomer_name(),
                        ticket.getCustomer_email(), StatusTicketEnum.StatusTicket.INITIAL, 1);

                Long newNumOfTicket = transCounter.getNumOfTicket() + 1;
                transCounter.setNumOfTicket(newNumOfTicket);
                transCounterData.save(transCounter);
                ticketData.save(ticketNew);
                // socketService.sendMessage(transCounter.getId().toString(), "get_message",
                // null, null);

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
    public BaseResponse updateTicket(Long id, UpdateTicketRequest request) {
        try {
            BaseResponse response = new BaseResponse();
            response.setData(ticketData.findById(id)
                    .map(oldItem -> {
                        Ticket newTicket = new Ticket();
                        newTicket.setId(id);
                        newTicket.setCreatedAt(oldItem.getCreatedAt());
                        newTicket.setCustomerEmail(request.getCustomerEmail());
                        newTicket.setCustomerName(request.getCustomerName());
                        newTicket.setIdTransCounter(request.getIdTransCounter());
                        newTicket.setProduct(request.getProduct());
                        newTicket.setRate(request.getRate());
                        newTicket.setStatus(request.getStatus());
                        newTicket.setUpdatedAt(request.getUpdatedAt());
                        return ticketData.save(newTicket);
                    }));
            response.setErrorCode("0");
            response.setErrorDesc("Thành công");
            return response;
        } catch (Exception e) {
            BaseResponse response = new BaseResponse();
            response.setErrorCode("1");
            response.setErrorDesc(" thất bại");
            return response;
        }
    }

    @Override
    public BaseResponse deleteTicket(Long id) {
        try {

            BaseResponse response = new BaseResponse();
            List<Ticket> ticketExist = ticketData.findByIdProduct(id);
            if (!ticketExist.isEmpty()) {
                response.setErrorCode("1");
                response.setErrorDesc(" thất bại");
            } else {
                ticketData.deleteById(id);
                response.setErrorCode("0");
                response.setErrorDesc("Thành công");
            }

            return response;
        } catch (Exception e) {
            return null;

        }
    }

}

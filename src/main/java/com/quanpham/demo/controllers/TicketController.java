package com.quanpham.demo.controllers;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quanpham.demo.BaseRespone.request.TicketRequest;
import com.quanpham.demo.BaseRespone.request.UpdateTicketRequest;
import com.quanpham.demo.BaseRespone.response.BaseResponse;
import com.quanpham.demo.BaseRespone.response.TicketResponse;
import com.quanpham.demo.models.Ticket;
import com.quanpham.demo.services.ITicketService;

@CrossOrigin("*")
@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private ITicketService ticketService;

    @RolesAllowed("ADMIN")
    @GetMapping("/get-all")
    public ResponseEntity<BaseResponse> getAllTickets() {
        return new ResponseEntity<>(this.ticketService.getAllTickets(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> getTicketById(@PathVariable("id") Long id) {

        return ResponseEntity.ok(this.ticketService.getTicketById(id));
    }

    @GetMapping("/get-by-idTransCounter")
    public ResponseEntity<BaseResponse> getTicketByIdTransCounter(@RequestParam("idTransCounter") Long idTransCounter) {

        return ResponseEntity.ok(this.ticketService.getTicketByIdTransCounter(idTransCounter));
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> addTicket(@Valid @RequestBody TicketRequest Ticket) {
        return ResponseEntity.ok(ticketService.create(Ticket));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateTicket(@PathVariable("id") Long id,
            @RequestBody UpdateTicketRequest request) {
        return ResponseEntity.ok(this.ticketService.updateTicket(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteTicket(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.ticketService.deleteTicket(id));
    }
}

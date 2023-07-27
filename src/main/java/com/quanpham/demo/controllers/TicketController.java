package com.quanpham.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/get-all")
    public ResponseEntity<BaseResponse> getAllTickets() {
        return new ResponseEntity<>(this.ticketService.getAllTickets(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> getTicketById(@PathVariable("id") String id) {

        return ResponseEntity.ok(this.ticketService.getTicketById(id));
    }

    @GetMapping("/get-by-user")
    public ResponseEntity<BaseResponse> getTicketByIdUser(@RequestParam("idUser") String idUser) {

        return ResponseEntity.ok(this.ticketService.getTicketByIdUser(idUser));
    }

    @PostMapping("/create")
    public ResponseEntity<TicketResponse> addTicket(@Valid @RequestBody TicketResponse Ticket) {
        return ResponseEntity.ok(ticketService.create(Ticket));
    }

    // @PutMapping("/{id}")
    // public BaseResponse updateTicket(@RequestBody Ticket request) {
    // return this.ticketService.updateTicket(request);
    // }

    // @DeleteMapping("/{id}")
    // public BaseResponse deleteTicket(@PathVariable("id") String id) {
    // return this.ticketService.deleteTicket(id);
    // }
}

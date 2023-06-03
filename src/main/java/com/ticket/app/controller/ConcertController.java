package com.ticket.app.controller;

import com.ticket.app.model.Concert;
import com.ticket.app.model.Ticket;
import com.ticket.app.service.ConcertService;
import com.ticket.app.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConcertController {
    private final ConcertService concertService;

    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }

    @GetMapping(value = "/concerts")
    public ResponseEntity<?> getActiveConcerts() {
        return ResponseUtil.returnResponse(HttpStatus.OK, concertService.getActiveConcerts());
    }

    @PostMapping("/tickets/book")
    public ResponseEntity<?> bookTicket(@RequestBody Concert request) {
        Ticket ticket = concertService.bookTicket(request.getId());
        if (ticket != null) {
            return ResponseUtil.returnResponse(HttpStatus.OK, ticket);
        } else {
            return ResponseUtil.returnErrorResponse(HttpStatus.NOT_FOUND, null);
        }
    }
}

package com.ticket.app.service;

import com.ticket.app.model.Concert;
import com.ticket.app.model.Ticket;
import com.ticket.app.repository.ConcertRepository;
import com.ticket.app.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcertService {
    private final ConcertRepository concertRepository;
    private final TicketRepository ticketRepository;

    public ConcertService(ConcertRepository concertRepository, TicketRepository ticketRepository) {
        this.concertRepository = concertRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<Concert> getActiveConcerts() {
        return concertRepository.findActiveConcert();
    }

    @Transactional
    public Ticket bookTicket(String concertId) {
        return concertRepository.findById(concertId)
                .filter(concert -> concert.getAvailableTickets() > 0)
                .map(concert -> {
                    concert.setAvailableTickets(concert.getAvailableTickets() - 1);
                    concertRepository.save(concert);

                    Ticket ticket = new Ticket();
                    ticket.setConcert(concert);
                    return ticketRepository.save(ticket);
                })
                .orElse(null);
    }
}

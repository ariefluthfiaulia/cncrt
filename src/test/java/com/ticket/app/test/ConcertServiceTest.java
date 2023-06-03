package com.ticket.app.test;

import com.ticket.app.model.Concert;
import com.ticket.app.model.Ticket;
import com.ticket.app.repository.ConcertRepository;
import com.ticket.app.repository.TicketRepository;
import com.ticket.app.service.ConcertService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ConcertServiceTest {
    @Mock
    private ConcertRepository concertRepository;

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private ConcertService concertService;

    @Test
    public void testGetActiveConcerts() {
        Concert concert1 = Concert.builder()
                .name("Coldplay Concert")
                .availableTickets(10000)
                .build();
        Concert concert2 = Concert.builder()
                .name("Stand Up Comedy Show")
                .availableTickets(15)
                .build();
        List<Concert> concerts = Arrays.asList(concert1, concert2);

        when(concertRepository.findActiveConcert()).thenReturn(concerts);

        List<Concert> result = concertService.getActiveConcerts();

        assertEquals(concerts.size(), result.size());
        assertEquals(concert1.getId(), result.get(0).getId());
        assertEquals(concert2.getId(), result.get(1).getId());
    }

    @Test
    public void testBookTicket_PositiveCase() {
        // Mock concert
        Concert concert = Concert.builder().id("1").availableTickets(2).build();

        // Mock ticket
        Ticket ticket = Ticket.builder().concert(concert).build();

        // Mock repository methods
        when(concertRepository.findById("1")).thenReturn(Optional.of(concert));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        // Call the bookTicket method
        Ticket result = concertService.bookTicket("1");

        // Verify the interactions and assertions
        assertNotNull(result);
        assertEquals(concert, result.getConcert());
        assertEquals(1, concert.getAvailableTickets());

        verify(concertRepository, times(1)).findById("1");
        verify(ticketRepository, times(1)).save(any(Ticket.class));
        verify(concertRepository, times(1)).save(concert);
    }

    @Test
    public void testBookTicket_NegativeCase_ConcertNotFound() {
        // Mock repository methods
        when(concertRepository.findById("1")).thenReturn(Optional.empty());

        // Call the bookTicket method
        Ticket result = concertService.bookTicket("1");

        // Verify the interactions and assertions
        assertNull(result);

        verify(concertRepository, times(1)).findById("1");
        verify(ticketRepository, never()).save(any(Ticket.class));
        verify(concertRepository, never()).save(any(Concert.class));
    }

    @Test
    public void testBookTicket_NegativeCase_NoAvailableTickets() {
        // Mock concert
        Concert concert = Concert.builder().id("1").availableTickets(0).build();

        // Mock repository methods
        when(concertRepository.findById("1")).thenReturn(Optional.of(concert));

        // Call the bookTicket method
        Ticket result = concertService.bookTicket("1");

        // Verify the interactions and assertions
        assertNull(result);

        verify(concertRepository, times(1)).findById("1");
        verify(ticketRepository, never()).save(any(Ticket.class));
        verify(concertRepository, never()).save(any(Concert.class));
    }
}

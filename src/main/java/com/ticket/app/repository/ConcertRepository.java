package com.ticket.app.repository;

import com.ticket.app.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, String> {
    @Query("SELECT c FROM Concert c WHERE c.availableTickets > 0")
    List<Concert> findActiveConcert();
}

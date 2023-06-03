package com.ticket.app.seeder;

import com.ticket.app.model.Concert;
import com.ticket.app.repository.ConcertRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseSeeder {
    private final ConcertRepository concertRepository;
    private final JdbcTemplate jdbcTemplate;

    public DatabaseSeeder(ConcertRepository concertRepository, JdbcTemplate jdbcTemplate) {
        this.concertRepository = concertRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedConcertTable();
    }

    private void seedConcertTable() {
        String sql = "SELECT * FROM concert c";
        List<Concert> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if (rs == null || rs.size() == 0) {
            concertRepository.save(Concert.builder()
                    .name("Coldplay Concert")
                    .availableTickets(10000)
                    .build());
        }
    }
}

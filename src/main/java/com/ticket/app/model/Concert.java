package com.ticket.app.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Table(name = "concert")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Concert {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "available_tickets", nullable = false)
    private int availableTickets;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "modified_date")
    private Date modifiedDate;

    @PrePersist
    void onCreate() {
        this.createdDate = new Date();
        this.modifiedDate = new Date();
    }

    @PreUpdate
    void onPersist() {
        this.modifiedDate = new Date();
    }
}

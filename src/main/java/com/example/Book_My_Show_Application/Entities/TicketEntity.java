package com.example.Book_My_Show_Application.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "Ticket")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String movieName;
    private String theatreName;
    private LocalTime showTime;
    private LocalDate showDate;
    private int totalAmount;
    private String ticketId= UUID.randomUUID().toString();
    private String bookedSeats;
    @ManyToOne
    @JoinColumn
    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn
    private ShowEntity showEntity;
}

package com.example.Book_My_Show_Application.Entities;

import com.example.Book_My_Show_Application.Enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "theatre_seats")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheatreSeatsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String seatNo;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    @ManyToOne
    @JoinColumn
    private TheatreEntity theatreEntity;
}

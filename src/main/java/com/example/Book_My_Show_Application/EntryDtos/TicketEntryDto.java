package com.example.Book_My_Show_Application.EntryDtos;

import lombok.Data;

import java.util.List;

@Data
public class TicketEntryDto {
    private int showId;
    private List<String> requestedSeats;
    private int userId;
}

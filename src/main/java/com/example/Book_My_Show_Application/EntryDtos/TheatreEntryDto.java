package com.example.Book_My_Show_Application.EntryDtos;

import lombok.Data;

@Data
public class TheatreEntryDto {
    private String Location;
    private String name;
    private int classicSeatsCount;
    private int premiumSeatsCount;
}

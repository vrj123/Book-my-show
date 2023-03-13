package com.example.Book_My_Show_Application.EntryDtos;

import com.example.Book_My_Show_Application.Enums.ShowType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowEntryDto {
    private LocalDate showDate;
    private LocalTime showTime;
    private ShowType showType;
    private int movieId;
    private int theaterId;
    private int premiumSeatPrice;
    private int classicSeatPrice;
}

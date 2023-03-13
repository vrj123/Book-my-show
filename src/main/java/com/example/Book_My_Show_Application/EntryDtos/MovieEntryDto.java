package com.example.Book_My_Show_Application.EntryDtos;

import com.example.Book_My_Show_Application.Enums.Genre;
import com.example.Book_My_Show_Application.Enums.Language;
import lombok.Data;

@Data
public class MovieEntryDto {
    private String name;
    private double ratings;
    private int duration;
    private Genre genre;
    private Language language;
}

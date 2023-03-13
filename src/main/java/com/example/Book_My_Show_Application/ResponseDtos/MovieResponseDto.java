package com.example.Book_My_Show_Application.ResponseDtos;

import com.example.Book_My_Show_Application.Enums.Genre;
import com.example.Book_My_Show_Application.Enums.Language;
import lombok.Data;

@Data
public class MovieResponseDto {
    private String name;
    private Language language;
    private Genre genre;
}

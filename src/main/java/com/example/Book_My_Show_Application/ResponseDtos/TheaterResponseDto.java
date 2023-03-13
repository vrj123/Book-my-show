package com.example.Book_My_Show_Application.ResponseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TheaterResponseDto {
    private String name;
    private String location;
}

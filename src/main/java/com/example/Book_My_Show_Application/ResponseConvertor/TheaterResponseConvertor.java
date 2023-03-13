package com.example.Book_My_Show_Application.ResponseConvertor;

import com.example.Book_My_Show_Application.Entities.TheatreEntity;
import com.example.Book_My_Show_Application.ResponseDtos.TheaterResponseDto;

public class TheaterResponseConvertor {
    static public TheaterResponseDto entityToDto(TheatreEntity theatreEntity){
        TheaterResponseDto theaterResponseDto= TheaterResponseDto.builder()
                .name(theatreEntity.getName())
                .location(theatreEntity.getLocation())
                .build();
        return theaterResponseDto;
    }
}

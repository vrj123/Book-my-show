package com.example.Book_My_Show_Application.Convertors;

import com.example.Book_My_Show_Application.Entities.TheatreEntity;
import com.example.Book_My_Show_Application.EntryDtos.TheatreEntryDto;

public class TheaterConvertor {
    public static TheatreEntity convertDtoToEntity(TheatreEntryDto theatreEntryDto){
        TheatreEntity theatreEntity=TheatreEntity.builder().Location(theatreEntryDto.getLocation())
                .name(theatreEntryDto.getName()).build();
        return theatreEntity;
    }
}

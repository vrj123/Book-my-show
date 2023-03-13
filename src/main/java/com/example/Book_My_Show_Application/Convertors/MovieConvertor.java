package com.example.Book_My_Show_Application.Convertors;

import com.example.Book_My_Show_Application.Entities.MovieEntity;
import com.example.Book_My_Show_Application.EntryDtos.MovieEntryDto;

public class MovieConvertor {
    public static MovieEntity convertDtoToEntity(MovieEntryDto movieEntryDto){
        MovieEntity movieEntity=MovieEntity.builder().genre(movieEntryDto.getGenre()).name(movieEntryDto.getName())
                .duration(movieEntryDto.getDuration()).language(movieEntryDto.getLanguage())
                .ratings(movieEntryDto.getRatings()).build();
        return movieEntity;
    }
}

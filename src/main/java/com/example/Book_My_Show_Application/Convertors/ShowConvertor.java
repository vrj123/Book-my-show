package com.example.Book_My_Show_Application.Convertors;

import com.example.Book_My_Show_Application.Entities.ShowEntity;
import com.example.Book_My_Show_Application.EntryDtos.ShowEntryDto;

public class ShowConvertor {
    public static ShowEntity dtoToEntity(ShowEntryDto showEntryDto){
        ShowEntity showEntity=ShowEntity.builder()
                .showTime(showEntryDto.getShowTime())
                .showDate(showEntryDto.getShowDate())
                .showType(showEntryDto.getShowType())
                .build();
        return showEntity;
    }
}

package com.example.Book_My_Show_Application.Convertors;

import com.example.Book_My_Show_Application.Entities.UserEntity;
import com.example.Book_My_Show_Application.EntryDtos.UserEntryDto;

public class UserConvertor {
    public static UserEntity convertDtoToEntity(UserEntryDto userEntryDto){
        UserEntity userEntity=UserEntity.builder().age(userEntryDto.getAge()).mobNo(userEntryDto.getMobNo())
                .name(userEntryDto.getName()).email(userEntryDto.getEmail()).address(userEntryDto.getAddress())
                .build();
        return userEntity;
    }
}

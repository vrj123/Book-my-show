package com.example.Book_My_Show_Application.Services;

import com.example.Book_My_Show_Application.Convertors.UserConvertor;
import com.example.Book_My_Show_Application.Entities.UserEntity;
import com.example.Book_My_Show_Application.EntryDtos.UserEntryDto;
import com.example.Book_My_Show_Application.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public String addUser(UserEntryDto userEntryDto) throws Exception{
        UserEntity userEntity= UserConvertor.convertDtoToEntity(userEntryDto);
        userRepository.save(userEntity);
        return "User added successfully";
    }
}

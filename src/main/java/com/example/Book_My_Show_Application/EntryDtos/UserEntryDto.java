package com.example.Book_My_Show_Application.EntryDtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserEntryDto {
    private String name;
    private int age;
    private String email;
    private String mobNo;
    private String address;
}

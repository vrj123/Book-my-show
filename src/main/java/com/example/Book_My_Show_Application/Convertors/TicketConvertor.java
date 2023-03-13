package com.example.Book_My_Show_Application.Convertors;

import com.example.Book_My_Show_Application.Entities.TicketEntity;
import com.example.Book_My_Show_Application.EntryDtos.TicketEntryDto;

public class TicketConvertor {
    public static TicketEntity dtoToEntity(TicketEntryDto ticketEntryDto){
        TicketEntity ticketEntity=new TicketEntity();
        return ticketEntity;
    }
}

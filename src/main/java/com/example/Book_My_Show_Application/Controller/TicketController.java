package com.example.Book_My_Show_Application.Controller;

import com.example.Book_My_Show_Application.EntryDtos.TicketEntryDto;
import com.example.Book_My_Show_Application.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @PostMapping("/book")
    public ResponseEntity<String> bookShow(@RequestBody TicketEntryDto ticketEntryDto){
        try {
            String response=ticketService.bookShow(ticketEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> cancelTicket(@RequestParam int ticketId){
        String response=ticketService.cancelTicket(ticketId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

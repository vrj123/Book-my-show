package com.example.Book_My_Show_Application.Controller;

import com.example.Book_My_Show_Application.EntryDtos.ShowEntryDto;
import com.example.Book_My_Show_Application.GetDtos.ShowTimeDto;
import com.example.Book_My_Show_Application.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    ShowService showService;
    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody ShowEntryDto showEntryDto){
        String response=showService.addShow(showEntryDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/getTimes")
    public ResponseEntity<List<LocalTime>> getShowTime(@RequestBody ShowTimeDto showTimeDto){
        try{
            List<LocalTime> timeList=showService.getShowTime(showTimeDto);
            return new ResponseEntity<>(timeList, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}

package com.example.Book_My_Show_Application.Controller;

import com.example.Book_My_Show_Application.EntryDtos.TheatreEntryDto;
import com.example.Book_My_Show_Application.ResponseDtos.TheaterResponseDto;
import com.example.Book_My_Show_Application.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/theatre")
public class TheaterController {
    @Autowired
    TheaterService theaterService;
    @PostMapping("/add")
    public ResponseEntity<String> addTheater(@RequestBody TheatreEntryDto theatreEntryDto){
        String response= theaterService.addTheater(theatreEntryDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/allLocations")
    public ResponseEntity<List<String>> getAllLocations(){
        List<String> locationList=theaterService.getAllLocations();
        return new ResponseEntity<>(locationList, HttpStatus.FOUND);
    }
    @GetMapping("/getShowTime/{time}")
    public ResponseEntity<List<TheaterResponseDto>> getTheaterWithShowTime(@PathVariable("time") LocalTime time){
        List<TheaterResponseDto> response=theaterService.getTheaterWithShowTime(time);
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }
}

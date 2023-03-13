package com.example.Book_My_Show_Application.Controller;

import com.example.Book_My_Show_Application.EntryDtos.MovieEntryDto;
import com.example.Book_My_Show_Application.ResponseDtos.MovieResponseDto;
import com.example.Book_My_Show_Application.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;
    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDto movieEntryDto){
        try {
            String response= movieService.addMovie(movieEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            String error="Movie not added";
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/maxMovie")
    public ResponseEntity<MovieResponseDto> movieWithMaxShow(){
        MovieResponseDto response=movieService.movieWithMaxShow();
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }
    @GetMapping("/revenue/{movieId}")
    public ResponseEntity<Integer> getRevenue(@PathVariable int movieId){
        return new ResponseEntity<>(movieService.getRevenue(movieId), HttpStatus.FOUND);
    }
}

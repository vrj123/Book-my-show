package com.example.Book_My_Show_Application.Services;

import com.example.Book_My_Show_Application.Convertors.MovieConvertor;
import com.example.Book_My_Show_Application.Entities.MovieEntity;
import com.example.Book_My_Show_Application.Entities.ShowEntity;
import com.example.Book_My_Show_Application.Entities.TicketEntity;
import com.example.Book_My_Show_Application.EntryDtos.MovieEntryDto;
import com.example.Book_My_Show_Application.Repository.MovieRepository;
import com.example.Book_My_Show_Application.ResponseDtos.MovieResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public String addMovie(MovieEntryDto movieEntryDto) throws Exception{
        MovieEntity movieEntity= MovieConvertor.convertDtoToEntity(movieEntryDto);
        movieRepository.save(movieEntity);
        return "Movie added successfully";
    }
    public MovieResponseDto movieWithMaxShow(){
        List<MovieEntity> movieEntityList=movieRepository.findAll();
        int max=0;
        MovieEntity ans=null;
        for (MovieEntity movieEntity:movieEntityList){
            int size=movieEntity.getShowEntityList().size();
            if(size>max){
                max=size;
                ans=movieEntity;
            }
        }
        MovieResponseDto movieResponseDto=new MovieResponseDto();
        movieResponseDto.setName(ans.getName());
        movieResponseDto.setGenre(ans.getGenre());
        movieResponseDto.setLanguage(ans.getLanguage());
        return movieResponseDto;
    }
    public int getRevenue(int movieId){
        MovieEntity movieEntity=movieRepository.findById(movieId).get();
        List<ShowEntity> showEntityList=movieEntity.getShowEntityList();
        int amount=0;
        for (ShowEntity showEntity:showEntityList){
            List<TicketEntity> ticketEntityList=showEntity.getTicketEntityList();
            for(TicketEntity ticketEntity:ticketEntityList){
                amount+=ticketEntity.getTotalAmount();
            }
        }
        return amount;
    }
}

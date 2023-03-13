package com.example.Book_My_Show_Application.Services;

import com.example.Book_My_Show_Application.Convertors.ShowConvertor;
import com.example.Book_My_Show_Application.Entities.*;
import com.example.Book_My_Show_Application.EntryDtos.ShowEntryDto;
import com.example.Book_My_Show_Application.Enums.SeatType;
import com.example.Book_My_Show_Application.GetDtos.ShowTimeDto;
import com.example.Book_My_Show_Application.Repository.MovieRepository;
import com.example.Book_My_Show_Application.Repository.ShowRepository;
import com.example.Book_My_Show_Application.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    ShowRepository showRepository;
    public String addShow(ShowEntryDto showEntryDto){
        ShowEntity showEntity= ShowConvertor.dtoToEntity(showEntryDto);
        int movieId=showEntryDto.getMovieId();
        int theaterId=showEntryDto.getTheaterId();
        MovieEntity movieEntity=movieRepository.findById(movieId).get();
        TheatreEntity theatreEntity=theaterRepository.findById(theaterId).get();
        showEntity.setMovieEntity(movieEntity);
        showEntity.setTheatreEntity(theatreEntity);
        List<ShowSeatEntity> showSeatEntityList=createShowSeat(showEntryDto, showEntity);
        showEntity.setShowSeatEntityList(showSeatEntityList);
        showEntity=showRepository.save(showEntity);
        theatreEntity.getShowEntityList().add(showEntity);
        movieEntity.getShowEntityList().add(showEntity);
        theaterRepository.save(theatreEntity);
        movieRepository.save(movieEntity);
        return "Show added successfully";
    }
    public List<ShowSeatEntity> createShowSeat(ShowEntryDto showEntryDto, ShowEntity showEntity){
        List<ShowSeatEntity> showSeatEntityList=new ArrayList<>();
        List<TheatreSeatsEntity> theatreSeatsEntityList=showEntity.getTheatreEntity().getTheatreSeatsEntityList();
        for(TheatreSeatsEntity theatreSeatsEntity:theatreSeatsEntityList){
            ShowSeatEntity showSeatEntity=new ShowSeatEntity();
            showSeatEntity.setBooked(false);
            showSeatEntity.setSeatNo(theatreSeatsEntity.getSeatNo());
            showSeatEntity.setSeatType(theatreSeatsEntity.getSeatType());
            if(theatreSeatsEntity.getSeatType().equals(SeatType.PREMIUM))
                showSeatEntity.setPrice(showEntryDto.getPremiumSeatPrice());
            else
                showSeatEntity.setPrice(showEntryDto.getClassicSeatPrice());
            showSeatEntity.setShowEntity(showEntity);
            showSeatEntityList.add(showSeatEntity);
        }
        return showSeatEntityList;
    }
    public List<LocalTime> getShowTime(ShowTimeDto showTimeDto){
        List<LocalTime> localTimeList=new ArrayList<>();
        int theaterId=showTimeDto.getTheaterId();
        int movieId=showTimeDto.getMovieId();
        TheatreEntity theatreEntity=theaterRepository.findById(theaterId).get();
        List<ShowEntity> showEntityList=theatreEntity.getShowEntityList();
        for (ShowEntity showEntity:showEntityList){
            if(showEntity.getMovieEntity().getId()==movieId)
                localTimeList.add(showEntity.getShowTime());
        }
        return localTimeList;
    }
}

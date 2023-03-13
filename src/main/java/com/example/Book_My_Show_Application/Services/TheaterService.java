package com.example.Book_My_Show_Application.Services;

import com.example.Book_My_Show_Application.Convertors.TheaterConvertor;
import com.example.Book_My_Show_Application.Entities.ShowEntity;
import com.example.Book_My_Show_Application.Entities.TheatreEntity;
import com.example.Book_My_Show_Application.Entities.TheatreSeatsEntity;
import com.example.Book_My_Show_Application.EntryDtos.TheatreEntryDto;
import com.example.Book_My_Show_Application.Enums.SeatType;
import com.example.Book_My_Show_Application.Repository.TheaterRepository;
import com.example.Book_My_Show_Application.Repository.TheaterSeatRepository;
import com.example.Book_My_Show_Application.ResponseConvertor.TheaterResponseConvertor;
import com.example.Book_My_Show_Application.ResponseDtos.TheaterResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    TheaterSeatRepository theaterSeatRepository;
    public String addTheater(TheatreEntryDto theatreEntryDto){
        TheatreEntity theatreEntity= TheaterConvertor.convertDtoToEntity(theatreEntryDto);
        List<TheatreSeatsEntity> theatreSeatsEntityList=createSeat(theatreEntryDto, theatreEntity);
        theatreEntity.setTheatreSeatsEntityList(theatreSeatsEntityList);
        theaterRepository.save(theatreEntity);
        return "Theater added successfully";
    }
    public List<TheatreSeatsEntity> createSeat(TheatreEntryDto theatreEntryDto, TheatreEntity theatreEntity){
        List<TheatreSeatsEntity> theatreSeatsEntityList=new ArrayList<>();
        int classicSeats=theatreEntryDto.getClassicSeatsCount();
        int premiumSeats=theatreEntryDto.getPremiumSeatsCount();
        for(int count=1; count<=classicSeats; count++){
            TheatreSeatsEntity theatreSeatsEntity=TheatreSeatsEntity.builder().seatNo(count+"C").seatType(SeatType.CLASSIC).theatreEntity(theatreEntity).build();
            theatreSeatsEntityList.add(theatreSeatsEntity);
        }
        for(int count=1; count<=premiumSeats; count++){
            TheatreSeatsEntity theatreSeatsEntity=TheatreSeatsEntity.builder().seatNo(count+"P").seatType(SeatType.PREMIUM).theatreEntity(theatreEntity).build();
            theatreSeatsEntityList.add(theatreSeatsEntity);
        }
//        theaterSeatRepository.saveAll(theatreSeatsEntityList);
        return theatreSeatsEntityList;
    }
    public List<String> getAllLocations(){
        List<String> locationList=theaterRepository.findUniqueLocations();
        return locationList;
    }
    public List<TheaterResponseDto> getTheaterWithShowTime(LocalTime time){
        List<TheaterResponseDto> theaterList=new ArrayList<>();
        List<TheatreEntity> theatreEntityList=theaterRepository.findAll();
        for(TheatreEntity theatreEntity:theatreEntityList){
            List<ShowEntity> showEntityList=theatreEntity.getShowEntityList();
            for(ShowEntity showEntity:showEntityList){
                if (showEntity.getShowTime().equals(time)){
                    theaterList.add(TheaterResponseConvertor.entityToDto(theatreEntity));
                    break;
                }
            }
        }
        return theaterList;
    }
}

package com.example.Book_My_Show_Application.Services;

import com.example.Book_My_Show_Application.Convertors.TicketConvertor;
import com.example.Book_My_Show_Application.Entities.*;
import com.example.Book_My_Show_Application.EntryDtos.TicketEntryDto;
import com.example.Book_My_Show_Application.Repository.ShowRepository;
import com.example.Book_My_Show_Application.Repository.ShowSeatRepository;
import com.example.Book_My_Show_Application.Repository.TicketRepository;
import com.example.Book_My_Show_Application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    ShowRepository showRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    ShowSeatRepository showSeatRepository;
    public String bookShow(TicketEntryDto ticketEntryDto) throws Exception{
        TicketEntity ticketEntity= TicketConvertor.dtoToEntity(ticketEntryDto);
        int showId=ticketEntryDto.getShowId();
        ShowEntity showEntity=showRepository.findById(showId).get();
        int totalAmount=0;
        if (isSeatsAvailable(showEntity, ticketEntryDto)){
            List<String> requestedSeats=ticketEntryDto.getRequestedSeats();
            List<ShowSeatEntity> showSeatEntityList=showEntity.getShowSeatEntityList();
            for(ShowSeatEntity showSeatEntity:showSeatEntityList){
                String seatNo=showSeatEntity.getSeatNo();
                if(requestedSeats.contains(seatNo)){
                    totalAmount+=showSeatEntity.getPrice();
                    showSeatEntity.setBooked(true);
                    showSeatEntity.setBookedAt(new Date());
                }
            }
        }
        else{
            throw new Exception("Requested seats are not available");
        }
        MovieEntity movieEntity=showEntity.getMovieEntity();
        TheatreEntity theatreEntity=showEntity.getTheatreEntity();
        int userId=ticketEntryDto.getUserId();
        UserEntity userEntity=userRepository.findById(userId).get();
        ticketEntity.setMovieName(movieEntity.getName());
        ticketEntity.setTheatreName(theatreEntity.getName());
        ticketEntity.setTotalAmount(totalAmount);
        ticketEntity.setShowDate(showEntity.getShowDate());
        ticketEntity.setShowTime((showEntity.getShowTime()));
        ticketEntity.setBookedSeats(bookedSeats(ticketEntryDto.getRequestedSeats()));
        ticketEntity.setUserEntity(userEntity);
        ticketEntity.setShowEntity(showEntity);
        ticketEntity=ticketRepository.save(ticketEntity);
        userEntity.getBookedTickets().add(ticketEntity);
        showEntity.getTicketEntityList().add(ticketEntity);
        userRepository.save(userEntity);
        showRepository.save(showEntity);

        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("javijay876@gmail.com");
        mimeMessageHelper.setTo(userEntity.getEmail());
        mimeMessageHelper.setSubject("Confirmed Ticket");
        mimeMessageHelper.setText("Congratulations, your ticket has been booked for movie "+movieEntity.getName());
        javaMailSender.send(mimeMessage);
        return "seat booked successfully";
    }
    public boolean isSeatsAvailable(ShowEntity showEntity, TicketEntryDto ticketEntryDto){
        List<String> requestedSeats=ticketEntryDto.getRequestedSeats();
        List<ShowSeatEntity> showSeatEntityList=showEntity.getShowSeatEntityList();
        List<String> seatList=new ArrayList<>();
        for(ShowSeatEntity showSeatEntity:showSeatEntityList){
            seatList.add(showSeatEntity.getSeatNo());
        }
        for(String seat: requestedSeats){
            if(!seatList.contains(seat)) return false;
        }
        for(ShowSeatEntity showSeatEntity:showSeatEntityList){
            String seatNo=showSeatEntity.getSeatNo();
            if(requestedSeats.contains(seatNo) && showSeatEntity.isBooked()) return false;
        }
        return true;
    }
    public String bookedSeats(List<String> seats){
        String seat="";
        for(String s:seats){
            seat=seat+", "+s;
        }
        return seat;
    }
    public String cancelTicket(int ticketId){
        TicketEntity ticketEntity=ticketRepository.findById(ticketId).get();
        String[] seats=ticketEntity.getBookedSeats().split(",");
        ShowEntity showEntity=ticketEntity.getShowEntity();
        int showId=showEntity.getId();
//        List<ShowSeatEntity> showSeatEntityList=showEntity.getShowSeatEntityList();
        for(int i=1; i<seats.length; i++){
            ShowSeatEntity showSeatEntity=showSeatRepository.findShowSeat(seats[i].substring(1), showId);
            showSeatEntity.setBooked(false);
            showSeatEntity.setBookedAt(null);
            showSeatRepository.save(showSeatEntity);
        }
        ticketRepository.delete(ticketEntity);
        return "Ticket deleted successfully";
    }
}

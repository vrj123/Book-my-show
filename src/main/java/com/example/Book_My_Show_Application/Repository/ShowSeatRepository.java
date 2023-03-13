package com.example.Book_My_Show_Application.Repository;

import com.example.Book_My_Show_Application.Entities.ShowSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeatEntity, Integer> {
    @Query(value = "select * from showseats where seat_no=:seatNo and show_entity_id=:showId", nativeQuery = true)
    ShowSeatEntity findShowSeat(String seatNo, int showId);
}

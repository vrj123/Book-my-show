package com.example.Book_My_Show_Application.Repository;

import com.example.Book_My_Show_Application.Entities.TheatreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<TheatreEntity, Integer> {
    @Query(value = "select distinct(location) from theatre", nativeQuery = true)
    List<String> findUniqueLocations();
}

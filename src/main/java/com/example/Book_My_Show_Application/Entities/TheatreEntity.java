package com.example.Book_My_Show_Application.Entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Theatre")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheatreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String Location;
    @OneToMany(mappedBy = "theatreEntity", cascade = CascadeType.ALL)
    private List<TheatreSeatsEntity> theatreSeatsEntityList=new ArrayList<>();
    @OneToMany(mappedBy = "theatreEntity", cascade = CascadeType.ALL)
    private List<ShowEntity> showEntityList=new ArrayList<>();
}

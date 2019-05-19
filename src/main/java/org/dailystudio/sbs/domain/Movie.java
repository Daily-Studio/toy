package org.dailystudio.sbs.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "MOVIE")
@NoArgsConstructor
public class Movie {

    @Id
    @Column(name = "MOVIE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "MOVIE_NAME")
    private String name;

    public Movie(String name){
        this.name = name;
    }
}

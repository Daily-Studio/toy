package org.dailystudio.sbs.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "MOVIE")
public class Movie {

    @Id
    @Column(name = "MOVIE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @Column(name = "MOVIE_NAME")
    private String name;

    public Movie(@NotNull String name) {
        this.name = name;
    }
}

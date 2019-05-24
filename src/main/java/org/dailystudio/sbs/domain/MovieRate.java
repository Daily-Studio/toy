package org.dailystudio.sbs.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "MOVIE_RATE")
public class MovieRate {

    @Id
    @Column(name = "MOVIERATE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @NotNull
    @Column(name = "MOVIERATE_SCORE")
    private long score;
    //소문자 long은 기본값이 0
    //대문자 Long은 기본값이 null


    public MovieRate(@NotNull Movie movie, @NotNull Account account, @NotNull long score) {
        this.movie = movie;
        this.account = account;
        this.score = score;
    }
}

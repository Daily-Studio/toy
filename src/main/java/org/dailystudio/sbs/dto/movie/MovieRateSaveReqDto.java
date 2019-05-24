package org.dailystudio.sbs.dto.movie;

import lombok.Getter;

@Getter
public class MovieRateSaveReqDto {
    public MovieRateSaveReqDto(String movieName, String accountEmail, long score) {
        this.movieName = movieName;
        this.accountEmail = accountEmail;
        this.score = score;
    }

    private String movieName;
    private String accountEmail;
    private long score;
}

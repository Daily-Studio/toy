package org.dailystudio.sbs.dto.movie;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MovieSaveReqDto {


    private String movieName;

    public MovieSaveReqDto(String movieName) {
        this.movieName = movieName;
    }
}

package org.dailystudio.sbs.dto.movie;


import lombok.Getter;
import org.dailystudio.sbs.dto.common.ResponseDto;

@Getter
public class MovieListResDto implements ResponseDto {

    private String movieName;

    public MovieListResDto(String movieName) {
        this.movieName = movieName;
    }
}


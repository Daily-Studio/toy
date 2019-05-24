package org.dailystudio.sbs.dto.movie;

import lombok.Getter;
import org.dailystudio.sbs.dto.common.ResponseDto;

@Getter
public class MovieAvgScoreResDto implements ResponseDto {
    private String movieName;
    private double avgScore;

    public MovieAvgScoreResDto(String movieName, double avgScore) {
        this.movieName = movieName;
        this.avgScore = avgScore;
    }
}

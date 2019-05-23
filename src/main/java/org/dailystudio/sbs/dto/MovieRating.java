package org.dailystudio.sbs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieRating {
    private int rate;
    private String movieName;
    private Double avgScore;
}

package org.dailystudio.sbs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScoringMovieRequestDTO {
    private String accountEmail;
    private String movieName;
    private int score;
}

package org.dailystudio.sbs.dto;

import lombok.Getter;
import org.dailystudio.sbs.domain.Movie;

@Getter
public class InputMovieRequestDTO {
    private String name;

    public Movie toMovieEntity() {
        return new Movie(this.name);
    }
}

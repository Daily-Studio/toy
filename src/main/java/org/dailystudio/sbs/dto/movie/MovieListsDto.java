package org.dailystudio.sbs.dto.movie;

import java.util.ArrayList;
import java.util.List;

public class MovieListsDto {
    private List<MovieListResDto> movieListResDtoList;

    public List<MovieListResDto> getMovieListResDtoList(){
        return new ArrayList<MovieListResDto>(movieListResDtoList);
    }
}

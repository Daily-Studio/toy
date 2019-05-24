package org.dailystudio.sbs.dto.movie;

import lombok.Getter;

@Getter
public class MovieListReqDto {
    private String accountEmail;

    public MovieListReqDto(String accountEmail){
        this.accountEmail = accountEmail;
    }
}

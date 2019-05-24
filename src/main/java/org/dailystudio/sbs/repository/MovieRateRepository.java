package org.dailystudio.sbs.repository;

import org.dailystudio.sbs.domain.Movie;
import org.dailystudio.sbs.domain.MovieRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRateRepository extends JpaRepository<MovieRate,Long> {
    List<MovieRate> findByAccount_Email(String email);
    List<MovieRate> findByMovie_Name(String movieName);

}

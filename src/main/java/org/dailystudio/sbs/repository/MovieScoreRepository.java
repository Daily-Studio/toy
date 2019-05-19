package org.dailystudio.sbs.repository;

import org.dailystudio.sbs.domain.Account;
import org.dailystudio.sbs.domain.Movie;
import org.dailystudio.sbs.domain.MovieScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieScoreRepository extends JpaRepository<MovieScore, Long> {

    Optional<MovieScore> findByAccountAndMovie(Account account, Movie movie);
}

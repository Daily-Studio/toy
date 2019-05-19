package org.dailystudio.sbs.service;

import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.domain.Account;
import org.dailystudio.sbs.domain.Movie;
import org.dailystudio.sbs.domain.MovieScore;
import org.dailystudio.sbs.dto.InputMovieRequestDTO;
import org.dailystudio.sbs.dto.ScoringMovieRequestDTO;
import org.dailystudio.sbs.repository.AccountRepository;
import org.dailystudio.sbs.repository.MovieRepository;
import org.dailystudio.sbs.repository.MovieScoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final AccountRepository accountRepository;
    private final MovieScoreRepository movieScoreRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public Boolean inputMovie(InputMovieRequestDTO inputMovieRequestDTO) {

        String movieName = inputMovieRequestDTO.getName();

        if (movieRepository.findByName(movieName).isPresent()) {
            return false;
        }
        Movie movie = inputMovieRequestDTO.toMovieEntity();
        movieRepository.save(movie);
        return true;
    }

    //MovieScoreService 파일을 나눠서 하는게 좋을지.
    //아니면 어차피 Movie에 관련된 서비스니까 이름을 동일하게 다 떄려박는게나은지?? 현재는 후자
    @Transactional
    public Boolean scoringMovie(ScoringMovieRequestDTO scoringMovieRequestDTO) {

        String email = scoringMovieRequestDTO.getAccountEmail();
        String movieName = scoringMovieRequestDTO.getMovieName();
        int score = scoringMovieRequestDTO.getScore();

        Account account = accountRepository.findByEmail(email).orElseThrow(RuntimeException::new);
        Movie movie = movieRepository.findByName(movieName).orElseThrow(RuntimeException::new);

        if (movieScoreRepository.findByAccountAndMovie(account, movie).isPresent()) {
            return false;
        }
        MovieScore movieScore = new MovieScore(score, movie, account);
        movieScoreRepository.save(movieScore);
        return true;
    }

    @Transactional
    public Boolean findMovieListScoredBy(Account account) {

        ArrayList<MovieScore> movieScoresList = (ArrayList) (movieScoreRepository.findAllByAccount(account).orElseThrow(RuntimeException::new));
        ArrayList<Movie> movieList = new ArrayList();

        for (movie:
             movieScoresList
        ) {


        }
    }
}

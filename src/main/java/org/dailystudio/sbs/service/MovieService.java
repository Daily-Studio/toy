package org.dailystudio.sbs.service;


import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.domain.Account;
import org.dailystudio.sbs.domain.Movie;
import org.dailystudio.sbs.domain.MovieRate;
import org.dailystudio.sbs.dto.account.AccountFindReqDto;
import org.dailystudio.sbs.dto.movie.MovieAvgScoreResDto;
import org.dailystudio.sbs.dto.movie.MovieListResDto;
import org.dailystudio.sbs.dto.movie.MovieRateSaveReqDto;
import org.dailystudio.sbs.dto.movie.MovieSaveReqDto;
import org.dailystudio.sbs.enums.ErrorCode;
import org.dailystudio.sbs.exception.business.entitynotfound.EntityNotFoundException;
import org.dailystudio.sbs.exception.business.invalidvalue.InvalidValueException;
import org.dailystudio.sbs.repository.AccountRepository;
import org.dailystudio.sbs.repository.MovieRateRepository;
import org.dailystudio.sbs.repository.MovieRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingLong;


@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieRateRepository movieRateRepository;
    private final AccountRepository accountRepository;


//    @Transactional
//    public  Optional<List<MovieRate>> findMovieByUser(final MovieListReqDto movieListReqDto){
//
//        final List<MovieRate> byAccount_email = movieRateRepository.findByAccount_Email(movieListReqDto.getAccountEmail());
//
//
//        return byAccount_email;
//    }


    @Transactional
    public boolean saveMovie (MovieSaveReqDto movieSaveReqDto){

        if (movieRepository.findByName(movieSaveReqDto.getMovieName()).isPresent())
            throw new InvalidValueException(ErrorCode.MOVIE_DUPLICATION);

        Movie movie = new Movie(movieSaveReqDto.getMovieName());
        movieRepository.save(movie);
        return true;
    }

    @Transactional
    public boolean saveMovieRate (MovieRateSaveReqDto movieRateSaveReqDto){

        Account account = accountRepository.findByEmail(movieRateSaveReqDto.getAccountEmail()).orElseThrow(() -> new EntityNotFoundException(ErrorCode.EMAIL_NOT_FOUND));
        Movie movie = movieRepository.findByName(movieRateSaveReqDto.getMovieName()).orElseThrow(()->new EntityNotFoundException(ErrorCode.MOVIE_NOT_FOUND));
        long score = movieRateSaveReqDto.getScore();

        MovieRate movieRate = new MovieRate(movie,account,score);
        movieRateRepository.save(movieRate);
        return true;
    }

    @Transactional
    public List<MovieListResDto> findMoviesByUser (AccountFindReqDto accountFindReqDto){

        List<MovieRate> movieRateList = movieRateRepository.findByAccount_Email(accountFindReqDto.getEmail()) ;
        if (movieRateList.isEmpty()) throw new EntityNotFoundException(ErrorCode.MOVIE_RATE_NOT_FOUND);

        List<Long> idList = movieRateList.stream()
                .map(a -> a.getMovie().getId())
                .collect(Collectors.toList());

        List<Movie> movieList = movieRepository.findAllById(idList);

        return movieList.stream()
                .map(m -> new MovieListResDto(m.getName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public MovieAvgScoreResDto findMovieAvgScore (MovieSaveReqDto movieSaveReqDto){
        List<MovieRate> movieRateList = movieRateRepository.findByMovie_Name(movieSaveReqDto.getMovieName()) ;
        if (movieRateList.isEmpty()) throw new EntityNotFoundException(ErrorCode.MOVIE_RATE_NOT_FOUND);

        Double avg = movieRateList.stream()
                .mapToLong(MovieRate::getScore)
                .average()
                .orElseThrow(RuntimeException::new);

        return new MovieAvgScoreResDto(movieSaveReqDto.getMovieName(),avg);
    }
    @Transactional
    public List<MovieAvgScoreResDto> findMoviesOrderByAvgScore (){

        List<MovieRate> movieRateList = movieRateRepository.findAll() ;
        List<Movie> movieList = movieRepository.findAll() ;
        if (movieList.isEmpty()) throw new EntityNotFoundException(ErrorCode.MOVIE_NOT_FOUND);

        final Map<@NotNull Movie, Double> scoreMap = movieRateList.stream()
                .collect(Collectors.groupingBy(MovieRate::getMovie, averagingLong(MovieRate::getScore)));

        return movieList.stream()
                .map( m -> new MovieAvgScoreResDto(m.getName(),Optional.ofNullable(scoreMap.get(m)).orElse(-1d) ))
                .sorted(Comparator.comparing(MovieAvgScoreResDto::getAvgScore).reversed())
                .collect(Collectors.toList());

    }
}

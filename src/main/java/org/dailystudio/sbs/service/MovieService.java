package org.dailystudio.sbs.service;

import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.domain.Account;
import org.dailystudio.sbs.domain.Movie;
import org.dailystudio.sbs.domain.MovieScore;
import org.dailystudio.sbs.dto.*;
import org.dailystudio.sbs.repository.AccountRepository;
import org.dailystudio.sbs.repository.MovieRepository;
import org.dailystudio.sbs.repository.MovieScoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<MovieUserScoredInfo> findMovieListScoredBy(String email) {

        Account account = accountRepository.findByEmail(email).orElseThrow(RuntimeException::new);

        List<MovieScore> movieScoresList =
                movieScoreRepository.findAllByAccount(account)
                        .orElseThrow(RuntimeException::new);

//        List<MovieUserScoredInfo> movieList =
//                movieScoresList.stream()
//                        .map(MovieScore::getMovie)
//                        .map(movie -> new MovieUserScoredInfo(movie.getName()))
//                        .collect(Collectors.toList());

        // 영화목록에서 영화이름만이 아니라 평점을 뭐로 매겻는지도 알려주고싶어서 아래처럼 수정함.
        List<MovieUserScoredInfo> movieList =
                movieScoresList.stream()
                        .map(movieScore ->
                                new MovieUserScoredInfo(
                                        movieScore.getMovie().getName(),
                                        movieScore.getScore())
                        )
                        .collect(Collectors.toList());

        return movieList;

//        어떤게 더 나음?? 변수에 넣어서 리턴?? 아니면 아래처럼 바로 리턴??
//        return movieScoresList.stream()
//                        .map(MovieScore::getMovie)
//                        .map(movie -> new MovieUserScoredInfo(movie.getName()))
//                        .collect(Collectors.toList());
    }

    public AvgScoreMovieResponseData avgScoreMovie(String movieName) {
        //
        // 추가하여야할 로직
        // 영화가 없는경우
        // 영화는 있는데 점수가 메겨지지 않은 경우
        //
        Movie movie = movieRepository.findByName(movieName).orElseThrow(RuntimeException::new);
        List<MovieScore> movieScores =
                movieScoreRepository.findByMovie(movie).orElseThrow(RuntimeException::new);

        Double avg = movieScores.stream()
                .mapToInt(MovieScore::getScore)
                .average()
                .orElseThrow(RuntimeException::new);

        return new AvgScoreMovieResponseData(avg);
    }

    // 랭킹순서 int 값 없음.
    // 모든 영화 평균평점을 평점 순으로 list 반환
    // 평점이 매겨지지 않은 영화는 평점 -1 반환
    public List<MovieRating> movieRatingList() {

        List<Movie> movieList = movieRepository.findAll();
        List<MovieScore> movieScoreList = movieScoreRepository.findAll();

        return
                movieList.stream()
                        .map(movie -> {
                                    double avgScore = movieScoreList.stream()
                                            .filter(movieScore ->
                                                    movieScore.getMovie().equals(movie))
                                            .mapToInt(MovieScore::getScore)
                                            .average()
                                            .orElse(-1d);
                                    return new MovieRating(movie.getName(), avgScore);
                                }
                        )
                        .sorted(Comparator.comparing(MovieRating::getAvgScore).reversed())
                        .collect(Collectors.toList());
    }
}

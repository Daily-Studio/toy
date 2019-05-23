package org.dailystudio.sbs.api;

import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.dto.*;
import org.dailystudio.sbs.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie")
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/input")
    public ResponseEntity<Boolean> inputMovie(@RequestBody InputMovieRequestDTO inputMovieRequestDTO) {
        Boolean result = movieService.inputMovie(inputMovieRequestDTO);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/scoring")
    public ResponseEntity<Boolean> scoringMovie(@RequestBody ScoringMovieRequestDTO scoringMovieRequestDTO) {
        Boolean result = movieService.scoringMovie(scoringMovieRequestDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/scored")
    public ResponseEntity<List<MovieUserScoredInfo>> getScoredMovieList(@RequestParam String email){
        List<MovieUserScoredInfo> result = movieService.findMovieListScoredBy(email);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/score")
    public ResponseEntity<AvgScoreMovieResponseData> avgScoreMovie(@RequestParam String movieName){
        AvgScoreMovieResponseData result = movieService.avgScoreMovie(movieName);
        return ResponseEntity.ok(result);
    }

    @GetMapping("test")
    public ResponseEntity<List<MovieRating>> test(){
        List<MovieRating> movieRatingList = movieService.movieRatingList();
        return ResponseEntity.ok(movieRatingList);
    }
}

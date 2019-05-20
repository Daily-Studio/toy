package org.dailystudio.sbs.api;

import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.domain.Movie;
import org.dailystudio.sbs.dto.AvgScoreMovieResponseData;
import org.dailystudio.sbs.dto.InputMovieRequestDTO;
import org.dailystudio.sbs.dto.MovieInfo;
import org.dailystudio.sbs.dto.ScoringMovieRequestDTO;
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
    public ResponseEntity<List<MovieInfo>> getScoredMovieList(@RequestParam String email){
        List<MovieInfo> result = movieService.findMovieListScoredBy(email);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/score")
    public ResponseEntity<AvgScoreMovieResponseData> avgScoreMovie(@RequestParam String movieName){
        AvgScoreMovieResponseData result = movieService.avgScoreMovie(movieName);
        return ResponseEntity.ok(result);
    }
}

package org.dailystudio.sbs.api;

import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.dto.InputMovieRequestDTO;
import org.dailystudio.sbs.dto.ScoringMovieRequestDTO;
import org.dailystudio.sbs.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

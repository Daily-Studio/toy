package org.dailystudio.sbs.api;


import lombok.RequiredArgsConstructor;
import org.dailystudio.sbs.dto.account.AccountFindReqDto;
import org.dailystudio.sbs.dto.common.APIResponseDto;
import org.dailystudio.sbs.dto.movie.MovieRateSaveReqDto;
import org.dailystudio.sbs.dto.movie.MovieSaveReqDto;
import org.dailystudio.sbs.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;



//    @GetMapping("/find")
//    public ResponseEntity<List<MovieRate>> findMovieByUser(@RequestParam("email") String accountEmail){
//        MovieListReqDto movieListReqDto = new MovieListReqDto(accountEmail);
//        return ResponseEntity.ok().body(movieService.findMovieByUser(movieListReqDto));
//    }

    @PostMapping("/save/movie/rate")
    public ResponseEntity<APIResponseDto> saveMovieRate(@RequestBody MovieRateSaveReqDto movieRateSaveReqDto){
        movieService.saveMovieRate(movieRateSaveReqDto);
        return ResponseEntity.ok().body(APIResponseDto.ofSuccess());
    }

    @PostMapping("/save/movie")
    public ResponseEntity<APIResponseDto> saveMovie(@RequestBody MovieSaveReqDto movieSaveReqDto){
        movieService.saveMovie(movieSaveReqDto);
        return ResponseEntity.ok().body(APIResponseDto.ofSuccess());
    }

    @GetMapping("/find/movies")
    public ResponseEntity<APIResponseDto> findMoviesByUser(@RequestParam("email") String email){
        AccountFindReqDto accountFindReqDto = new AccountFindReqDto(email);
        APIResponseDto apiResponseDto = APIResponseDto.ofSuccess(movieService.findMoviesByUser(accountFindReqDto)) ;
        return ResponseEntity.ok().body(apiResponseDto);
    }

    @GetMapping("/find/movie/score")
    public ResponseEntity<APIResponseDto> findMovieAvgScore(@RequestParam("movieName") String movieName){
        MovieSaveReqDto movieSaveReqDto = new MovieSaveReqDto(movieName);
        APIResponseDto apiResponseDto = APIResponseDto.ofSuccess(movieService.findMovieAvgScore(movieSaveReqDto)) ;
        return ResponseEntity.ok().body(apiResponseDto);
    }
    @GetMapping("/find/movies/orderby/score")
    public ResponseEntity<APIResponseDto> findMoviesOrderByAvgScore(){
        APIResponseDto apiResponseDto = APIResponseDto.ofSuccess(movieService.findMoviesOrderByAvgScore()) ;
        return ResponseEntity.ok().body(apiResponseDto);
    }

}

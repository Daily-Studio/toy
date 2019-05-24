package org.dailystudio.sbs;

import org.dailystudio.sbs.api.AccountController;
import org.dailystudio.sbs.api.MovieController;
import org.dailystudio.sbs.dto.account.AccountSaveReqDto;
import org.dailystudio.sbs.dto.movie.MovieRateSaveReqDto;
import org.dailystudio.sbs.dto.movie.MovieSaveReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SbsApplication {

	@Autowired
	AccountController accountController;
	@Autowired
	MovieController movieController;

	public static void main(String[] args) {

		SpringApplication.run(SbsApplication.class, args);
	}

	@PostConstruct
	public void ff(){
//		accountController.signUp(new AccountSaveReqDto("maenguin@gmail.com","1111","한맹희"));
//		accountController.signUp(new AccountSaveReqDto("pci@gmail.com","1111","박짜뉜"));
		movieController.saveMovie(new MovieSaveReqDto("아이언맨1"));
		movieController.saveMovie(new MovieSaveReqDto("아이언맨2"));
		movieController.saveMovie(new MovieSaveReqDto("아이언맨3"));
//
//		movieController.saveMovieRate(new MovieRateSaveReqDto("아이언맨1","maenguin@gmail.com",7));
//		movieController.saveMovieRate(new MovieRateSaveReqDto("아이언맨1","maenguin@gmail.com",8));
//		movieController.saveMovieRate(new MovieRateSaveReqDto("아이언맨1","pci@gmail.com",2));
//		movieController.saveMovieRate(new MovieRateSaveReqDto("아이언맨2","maenguin@gmail.com",10));
//		movieController.saveMovieRate(new MovieRateSaveReqDto("아이언맨2","pci@gmail.com",9));

	}

}

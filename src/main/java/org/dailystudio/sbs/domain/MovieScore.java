package org.dailystudio.sbs.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "MOVIE_SCORE",
        uniqueConstraints= {
        @UniqueConstraint(
                columnNames = {"MOVIE_ID", "ACCOUNT_ID"}
        )
})
@NoArgsConstructor //도메인에는 항상 기본생성자가 존재해야한대
public class MovieScore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MOVIE_SCORE_ID")
    private Long id;

    @NonNull
    @Column(name = "MOVIE_SCORE_SCORE")
    private int score;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")//외래키의 칼럼네임이래
    private Movie movie;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    public MovieScore(int score, Movie movie, Account account) {
        this.score = score;
        this.movie = movie;
        this.account = account;
    }
}

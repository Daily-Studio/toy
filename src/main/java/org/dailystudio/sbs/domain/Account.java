package org.dailystudio.sbs.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dailystudio.sbs.dto.account.AccountNameChangeReqDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ACCOUNT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long id;

    @Column(name = "ACCOUNT_EMAIL", unique = true)
    @NotNull
    private String email;

    @Column(name = "ACCOUNT_PASSWORD")
    @NotNull
    private String password;

    @Column(name = "ACCOUNT_NAME")
    @NotNull
    private String name;

    @Builder
    public Account(@NotNull String email, @NotNull String password, @NotNull String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public void changeName(AccountNameChangeReqDto reqDto) {
        this.name = reqDto.getName();
    }
}


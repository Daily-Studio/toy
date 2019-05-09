package org.dailystudio.sbs.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dailystudio.sbs.dto.AccountReqDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@Table(name = "account")
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private String id;

    @NotNull
    @Column(name = "account_email")
    private String email;
    @NotNull
    @Column(name = "account_password")
    private String password;
    @NotNull
    @Column(name = "account_name")
    private String name;

    @Builder
    public Account(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }


}


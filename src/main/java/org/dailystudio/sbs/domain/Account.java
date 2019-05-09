package org.dailystudio.sbs.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "account")
@NoArgsConstructor
@Getter
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;

    public Account(String email, String password, String name) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

}

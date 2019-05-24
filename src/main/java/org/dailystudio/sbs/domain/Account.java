package org.dailystudio.sbs.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dailystudio.sbs.dto.account.AccountUpdateNameDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@Table(name = "ACCOUNT")
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private String id;

    @NotNull
    @Column(name = "ACCOUNT_EMAL", unique = true)
    private String email;
    @NotNull
    @Column(name = "ACCOUNT_PASS")
    private String password;
    @NotNull
    @Column(name = "ACCOUNT_NAME")
    private String name;

    @Builder
    public Account(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public void SetName(AccountUpdateNameDto accountUpdateNameDto){
        this.name = accountUpdateNameDto.getName();
    }


}


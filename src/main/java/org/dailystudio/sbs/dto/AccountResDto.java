package org.dailystudio.sbs.dto;

import lombok.Getter;
import org.dailystudio.sbs.domain.Account;

@Getter
public class AccountResDto {
    private String email;
    private String name;

    public AccountResDto(String email, String name){
        this.email = email;
        this.name = name;
    }
    public AccountResDto(Account account){
        this.email = account.getEmail();
        this.email = account.getName();
    }
}

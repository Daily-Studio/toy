package org.dailystudio.sbs.dto;

import org.dailystudio.sbs.domain.Account;

public class AccountFindResDto {
    private String email;
    private String name;

    public  AccountFindResDto(Account account){
        this.email = account.getEmail();
        this.name = account.getName();
    }
}

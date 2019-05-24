package org.dailystudio.sbs.dto.account;

import lombok.Getter;

@Getter
public class AccountFindReqDto {
    private String email;

    public AccountFindReqDto(String email){
        this.email = email;
    }
}

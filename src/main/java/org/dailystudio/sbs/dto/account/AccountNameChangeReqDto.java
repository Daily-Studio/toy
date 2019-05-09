package org.dailystudio.sbs.dto.account;

import lombok.Getter;

@Getter
public class AccountNameChangeReqDto {
    private String email;
    private String password;
    private String name;
}

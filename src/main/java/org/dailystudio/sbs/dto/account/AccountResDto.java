package org.dailystudio.sbs.dto.account;

import lombok.Getter;
import org.dailystudio.sbs.domain.Account;

@Getter
public class AccountResDto {
    private String email;
    private String name;

    public AccountResDto(Account account) {
        this.email = account.getEmail();
        this.name = account.getName();
    }
}

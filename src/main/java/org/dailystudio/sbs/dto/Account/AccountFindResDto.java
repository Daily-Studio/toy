package org.dailystudio.sbs.dto.Account;

import lombok.Getter;
import org.dailystudio.sbs.domain.Account;

@Getter
public class AccountFindResDto {
    private String email;
    private String name;

    public  AccountFindResDto(Account account){
        this.email = account.getEmail();
        this.name = account.getName();
    }
}

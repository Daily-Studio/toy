package org.dailystudio.sbs.dto.account;

import lombok.Getter;
import org.dailystudio.sbs.domain.Account;
import org.dailystudio.sbs.dto.common.ResponseDto;

@Getter
public class AccountFindResDto implements ResponseDto {
    private String email;
    private String name;

    public  AccountFindResDto(Account account){
        this.email = account.getEmail();
        this.name = account.getName();
    }
}

package org.dailystudio.sbs.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AccountFindAllUserDto {

  private List<AccountResDto> accounts = new ArrayList<>();

    public AccountFindAllUserDto(List<AccountResDto> accounts) {

        this.accounts= accounts;
    }
}

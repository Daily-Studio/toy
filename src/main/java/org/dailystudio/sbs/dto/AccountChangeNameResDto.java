package org.dailystudio.sbs.dto;

import lombok.Getter;

@Getter
public class AccountChangeNameResDto {
  private String name;

    public AccountChangeNameResDto(String name) {
        this.name= name;
    }
}

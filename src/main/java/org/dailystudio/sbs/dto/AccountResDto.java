package org.dailystudio.sbs.dto;

import lombok.Getter;

@Getter
public class AccountResDto {
  //이메일이랑 이름만 !
   private String email;
   private String name;


    public AccountResDto(String email, String name) {
        this.email = email;
        this.name= name;
    }
}

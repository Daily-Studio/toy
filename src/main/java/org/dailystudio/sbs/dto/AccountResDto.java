package org.dailystudio.sbs.dto;

import lombok.Getter;

@Getter
public class AccountResDto {
  //이메일이랑 패스만 !
   private String email;
   private String name;


    public AccountResDto(String email, String pass) {
        this.email = email;
        this.name= pass;
    }
}

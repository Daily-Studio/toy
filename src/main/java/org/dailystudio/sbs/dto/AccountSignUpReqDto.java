package org.dailystudio.sbs.dto;

import lombok.Getter;
import org.dailystudio.sbs.domain.Account;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class AccountSignUpReqDto {
   //가입하기 위해 받는정보에는 id가필요없다. 데이터베이스에 저장할때 자동저장.
    // private Long id;
    private String email;
    private String name;
    private String pass;
/*
    public Account toEntity(PasswordEncoder passwordEncoder) {

        return Account.builder()
                .email(this.email)
                .pass(passwordEncoder.encode(this.pass))
                .build();
    }*/
}


package org.dailystudio.sbs.dto;

import lombok.Getter;
import org.dailystudio.sbs.domain.Account;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class AccountReqDto {

    private String email;
    private String password;
    private String name;


    public Account toEntity(PasswordEncoder passwordEncoder) {
        return Account.builder()
                .email(this.email)
                .name(this.name)
                .password(passwordEncoder.encode(this.password))
                .build();
    }
}
